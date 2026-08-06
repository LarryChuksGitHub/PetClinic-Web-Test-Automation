package com.tsys.testcommon.config.cloudprovider;

import java.util.Map;

public abstract class CloudTunnel {

    public abstract void startTunnel(Map<String, Object> cloudProviderOptions) throws Exception;
    public abstract void stopTunnel();

    public static CloudTunnel newTunnel() {
        if (Boolean.parseBoolean(System.getProperty("browserStackRun", "false"))) {
            return new BrowserstackTunnel();
        }
        if (Boolean.parseBoolean(System.getProperty("mobileDeviceCloudRun", "false"))) {
            return new MobileDeviceCloudTunnel();
        }
        throw new IllegalStateException("No cloud provider is configured!");
    }

}
