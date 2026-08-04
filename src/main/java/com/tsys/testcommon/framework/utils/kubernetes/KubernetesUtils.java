package com.tsys.testcommon.framework.utils.kubernetes;

import static com.tsys.testcommon.config.Config.isDev;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import com.tsys.testcommon.framework.utils.sleep.SleepUtil;
import com.tsys.testcommon.model.common.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.LocalPortForward;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KubernetesUtils {

     static final Map<Integer, Service> PORT_FORWARDED_SERVICES_MAP = new HashMap<>();
    private final static Map<Service, KubernetesClient> K8S_CLIENTS = new HashMap<>();
    public static final int PORT = 80;
    private static final int SLEEP = 100;
    private static final int EXEC_TIMEOUT_SECONDS = 30;
    private static final int KEY_VALUE_LEN = 2;
    private static LocalPortForward portForward;

    static KubernetesClient getK8sClient(Service service) {
        if (!K8S_CLIENTS.containsKey(service)) {
            K8S_CLIENTS.put(service, configureKubernetesClient(service));
        }
        return K8S_CLIENTS.get(service);
    }

    private KubernetesUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized void portForward(Service service, int port, int localPort) {

        boolean isPortAlreadyInUse = PORT_FORWARDED_SERVICES_MAP.computeIfPresent(localPort, (key, existingService) -> {
            if (!(existingService == service)) {
                throw new IllegalStateException(String.format("Local port %d is already forwarded by service %s",
                        localPort, existingService.getName()));
            }
            return existingService; // If the service is the same, return the existing service without changes
        }) != null;

        if (!isPortAlreadyInUse) {
            log.info("Port forwarding {}", service.getName());
            String serviceName = getServiceName(service);
            portForward = getK8sClient(service).services().withName(serviceName).portForward(port, localPort);
            log.info("Port forwarded for {} at http://127.0.0.1:{}", serviceName, portForward.getLocalPort());

            // Add service to map
            PORT_FORWARDED_SERVICES_MAP.put(localPort, service);
        }
    }

    public static void refreshKubernetesClient(Service service) {
        if (!K8S_CLIENTS.containsKey(service)) {
            return;
        }
        K8S_CLIENTS.get(service).close();
        K8S_CLIENTS.remove(service);
    }

    public static void closePortForwards() {
        if (portForward != null) {
            log.info("Closing port forwards");
            // Need to wait a little to not have exception
            SleepUtil.sleep(SLEEP);
            try {
                portForward.close();
                PORT_FORWARDED_SERVICES_MAP.clear();
            } catch (IOException e) {
                throw new IllegalStateException("Failed to close port forwards!", e);
            }
        }
    }

    private static synchronized KubernetesClient configureKubernetesClient(Service service) {

        String kubeConfigContent = new String(Base64.getDecoder().decode(getKubeConfig()), StandardCharsets.UTF_8);
        io.fabric8.kubernetes.client.Config kubeConfig = io.fabric8.kubernetes.client.Config.fromKubeconfig(kubeConfigContent);

        Assert.assertNotNull(kubeConfig, "Kubernetes config is not properly set! Add KUBECONFIG_* environment variable");
        Assert.assertNotNull(kubeConfig.getMasterUrl(), "Cannot find server in kubeconfig!");
        Assert.assertNotNull(kubeConfig.getCaCertData(), "Cannot find certificate-authority-data in kubeconfig!");
        // Kube config format is different for OSC TU env.


        try {
            return new KubernetesClientBuilder().withConfig(kubeConfig).build();
        } catch (KubernetesClientException e) {
            log.error("Kubernetes configuration is invalid!");
            throw new KubernetesClientException("Failed to create Kubernetes client!", e);
        }
    }

    private static boolean isClientCertDataExist(io.fabric8.kubernetes.client.Config kubeConfig) {
        return Objects.nonNull(kubeConfig.getClientCertData()) && Objects.nonNull(kubeConfig.getClientKeyData());
    }

    @SneakyThrows
    private static String getKubeConfig() {
        if ((isDev())) {
            return System.getenv("KUBECONFIG_STAGING");
        } else {
            return getDevOrLocalConfig();
        }
    }

    private static String getDevOrLocalConfig() throws IOException {
        String configDev = System.getenv("KUBECONFIG_DEV");
        // try to use local config
        if (StringUtils.isBlank(configDev)) {
            byte[] configBytes = Files.readAllBytes(Path.of(System.getProperty("user.home"), "/.kube", "config"));
            return Base64.getEncoder().encodeToString(configBytes);
        }
        return configDev;
    }

    private static String getServiceName(Service service) {
        if (service.getLabel() != null) {
            var services = getK8sClient(service).services()
                    .list()
                    .getItems();
            Optional<io.fabric8.kubernetes.api.model.Service> k9sService = services
                    .stream()
                    .filter(s -> s.getMetadata().getName().contains(service.getLabel()))
                    .findFirst();

            if (k9sService.isEmpty()) {
                return service.getName();
            }
            return k9sService.get().getMetadata().getName();

        } else {
            return service.getName();
        }
    }

    static class SimpleListener implements ExecListener {

        private final CompletableFuture<String> data;
        private final ByteArrayOutputStream outputStream;

        public SimpleListener(CompletableFuture<String> data, ByteArrayOutputStream outputStream) {
            this.data = data;
            this.outputStream = outputStream;
        }

        @Override
        public void onOpen() {
            // addition code on open
        }

        @Override
        public void onFailure(Throwable t, Response failureResponse) {
            log.error(t.getMessage());
            data.completeExceptionally(t);
        }

        @Override
        public void onClose(int code, String reason) {
            data.complete(outputStream.toString());
        }
    }
}
