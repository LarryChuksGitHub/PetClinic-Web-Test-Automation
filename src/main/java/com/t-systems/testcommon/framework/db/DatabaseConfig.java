package com.verimi.testcommon.framework.db;

import static org.postgresql.PGProperty.PG_PORT;

import java.util.Properties;
import java.util.Random;

import lombok.Data;

@Data
public class DatabaseConfig {

    /**
     * Can be 3 formats:
     * jdbc:postgresql:// for postgres connection
     * jdbc:mariadb:// for mariadb connection - not yet implemented
     * https:// for test data access service
     */
    private final String jdbcUrl;
    private final String jdbcLocalUrl;
    private final int localPort;
    private final int port;
    private final Type type;

    public DatabaseConfig(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        localPort = new Random().nextInt(40_000) + 10_000;
        jdbcLocalUrl = jdbcUrl.replaceAll("://.+:\\d+", "://127.0.0.1:" + localPort);

        if (jdbcUrl.startsWith("jdbc")) {
            Properties parsedURL = org.postgresql.Driver.parseURL(jdbcUrl, null);
            port = Integer.parseInt(parsedURL.getProperty(PG_PORT.getName(), "5432"));
            if (jdbcUrl.startsWith("jdbc:postgres")) {
                type = Type.POSTGRES;
            } else {
                type = Type.MARIADB;
            }
        } else {
            port = 80; // this is pod port, not externally visible ssl
            type = Type.TEST_DATA_ACCESS_SERVICE;
        }
    }

    public enum Type {
        POSTGRES, MARIADB, TEST_DATA_ACCESS_SERVICE
    }
}
