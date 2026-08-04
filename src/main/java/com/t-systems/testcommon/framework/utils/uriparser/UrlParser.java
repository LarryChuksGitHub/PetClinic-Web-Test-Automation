package com.verimi.testcommon.framework.utils.uriparser;

import static javax.swing.text.html.HTML.Tag.CODE;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UrlParser {

    private UrlParser() {
        throw new IllegalStateException("Utility class");
    }

    private static Map<String, String> getUrlParametersFromUrl(String stringUrl) {
        URL url = null;
        try {
            URI uri = new URI(stringUrl);
            url = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
        assert url != null;
        String query = url.getQuery();
        Map<String, String> map = new HashMap<>(12);

        String[] params = query.split("&");
        for (String param : params) {
            String[] splitedParams = param.split("=");
            String name = splitedParams[0];
            String value = splitedParams[1];
            map.put(name, value);
        }
        return map;
    }

    public static String getParameterFromUrlByCode(String stringUrl, String parameterId) {
        Map map = getUrlParametersFromUrl(stringUrl);
        if (!map.containsKey(parameterId)) {
            throw new IllegalArgumentException("No such code in URL");
        }
        return map.get(parameterId).toString();
    }

    public static String getCodeParameterFromUrl(String stringUrl) {
        Map<String, String> map = getUrlParametersFromUrl(stringUrl);
        if (!map.containsKey(CODE.toString())) {
            throw new IllegalArgumentException("No such code in URL");
        }
        return map.get(CODE.toString());
    }

}
