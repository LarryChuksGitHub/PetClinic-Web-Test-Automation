package com.petclinic.testcommon.framework.utils.gzip;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

import lombok.SneakyThrows;

public class GzipUtils {

    @SneakyThrows
    public static String gzipBase64Encode(String input) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(input.getBytes(StandardCharsets.ISO_8859_1));
        }
        return Base64.getEncoder().encodeToString(byteStream.toByteArray());
    }

}
