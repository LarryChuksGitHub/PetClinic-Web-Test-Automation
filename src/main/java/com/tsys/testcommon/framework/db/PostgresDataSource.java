package com.tsys.testcommon.framework.db;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

import com.tsys.testcommon.config.Config;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresDataSource {
    private static DataSource dataSource;
    private static PGSimpleDataSource dataSourceAnalyticsDatabase;
    private static PGSimpleDataSource dataSourceHydraDatabase;
    private static PGSimpleDataSource dataSourceHydraProxyDatabase;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = createDataSource(Config.getPostgresDbConfig());
        }

        return dataSource;
    }

    public static PGSimpleDataSource getDataSourceAnalyticsDatabase() {
        if (dataSourceAnalyticsDatabase == null) {
            dataSourceAnalyticsDatabase = createDataSource(Config.getAnalyticsDbConfig());
        }

        return dataSourceAnalyticsDatabase;
    }

    public static PGSimpleDataSource getDataSourceHydraDatabase() {
        if (dataSourceHydraDatabase == null) {
            dataSourceHydraDatabase = createDataSource(Config.getHydraDbConfig());
        }

        return dataSourceHydraDatabase;
    }

    public static PGSimpleDataSource getDataSourceHydraProxyDatabase() {
        if (dataSourceHydraProxyDatabase == null) {
            dataSourceHydraProxyDatabase = createDataSource(Config.getHydraProxyDbConfig());
        }

        return dataSourceHydraProxyDatabase;
    }

    private static PGSimpleDataSource createDataSource(DatabaseConfig config) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

            dataSource.setUrl(config.getJdbcUrl());
        log.info("Created data source: {}", dataSource);
        return dataSource;
    }

}
