package com.verimi.testcommon.framework.utils.dbutils;

import static com.verimi.testcommon.config.Config.getHydraDbConfig;
import static com.verimi.testcommon.config.Config.getPostgresDbConfig;
import static com.verimi.testcommon.model.common.Service.POSTGRES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.verimi.testcommon.framework.db.PostgresDataSource;
import com.verimi.testcommon.model.common.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionManager {

    public static final String DB_CONNECTION_FAILED_MESSAGE = "Failed to establish DB connection!";

    private static Connection postgresConnection;
    private static Connection connectionAnalyticsDatabase;
    private static Connection connectionHydraDatabase;
    private static Connection connectionHydraProxyDatabase;

    /**
     * Do not establish connection on set up as connection might be lost
     * for various reasons during suite run i.e. n/w issues, DB pod down etc
     *
     * @return
     */
    public static Connection getPostgresConnection() {
        if (postgresConnection == null) {
            postgresConnection = connectToDB();
        }
        return postgresConnection;
    }

    public static Connection getConnectionAnalyticsDatabase() {
        if (connectionAnalyticsDatabase == null) {
            connectionAnalyticsDatabase = connectToAnalyticsDatabase();
        }
        return connectionAnalyticsDatabase;
    }

    public static synchronized Connection getConnectionHydraDatabase() {
        if (connectionHydraDatabase == null) {
            connectionHydraDatabase = connectToHydraDatabase();
        }
        return connectionHydraDatabase;
    }

    public static synchronized Connection getConnectionHydraProxyDatabase() {
        if (connectionHydraProxyDatabase == null) {
            connectionHydraProxyDatabase = connectToHydraProxyDatabase();
        }
        return connectionHydraProxyDatabase;
    }

    public static void closeAllConnections() {
        try {
            if (postgresConnection != null) {
                postgresConnection.close();
            }
            if (connectionAnalyticsDatabase != null) {
                connectionAnalyticsDatabase.close();
            }
            if (connectionHydraDatabase != null) {
                connectionHydraDatabase.close();
            }
            if (connectionHydraProxyDatabase != null) {
                connectionHydraProxyDatabase.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection connectToDB() {
        log.info("Setting up database connections.");
        try {
            return PostgresDataSource.getDataSource().getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new IllegalStateException(DB_CONNECTION_FAILED_MESSAGE, e);
        }
    }

    private static Connection connectToAnalyticsDatabase() {
        log.info("Setting up database connections to analytics database.");
        Service service = POSTGRES;

        try {
            return PostgresDataSource.getDataSourceAnalyticsDatabase().getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new IllegalStateException(DB_CONNECTION_FAILED_MESSAGE, e);
        }
    }

    private static Connection connectToHydraDatabase() {
        log.info("Setting up database connections to hydra database.");
        Service service = POSTGRES;
        try {
            return PostgresDataSource.getDataSourceHydraDatabase().getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new IllegalStateException(DB_CONNECTION_FAILED_MESSAGE, e);
        }
    }

    private static Connection connectToHydraProxyDatabase() {
        log.info("Setting up database connections to hydra proxy database.");
        Service service = POSTGRES;
        try {
            return PostgresDataSource.getDataSourceHydraProxyDatabase().getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new IllegalStateException(DB_CONNECTION_FAILED_MESSAGE, e);
        }
    }

    public static Connection connectToDB(String env) throws SQLException {
        return DriverManager.getConnection(getPostgresDbConfig(env));
    }

    public static Connection connectToHydraDatabase(String env) throws SQLException {
        return DriverManager.getConnection(getHydraDbConfig(env));
    }

}
