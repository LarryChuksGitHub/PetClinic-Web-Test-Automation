package com.petclinic.testcommon.framework.db;

import com.petclinic.testcommon.framework.utils.dbutils.DBConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DatabaseManagerImpl implements DatabaseManager {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private int updateValue;

    protected DatabaseManagerImpl() {
    }

    public int getUpdateValue() {
        return updateValue;
    }

    @Deprecated
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    @Override
    public ResultSet executeQuery(IQuery query, Object... param) {
        connect();
        return execute(query, param);
    }

    @Override
    public ResultSet executeQueryAnalyticsDatabase(IQuery query, Object... param) {
        connectAnalyticsDataBase();
        return execute(query, param);
    }

    @Override
    public ResultSet executeQueryHydraDatabase(IQuery query, Object... param) {
        connectHydraDataBase();
        return execute(query, param);
    }

    @Override
    public ResultSet executeQueryHydraProxyDatabase(IQuery query, Object... param) {
        connectHydraProxyDataBase();
        return execute(query, param);
    }

    private ResultSet execute(IQuery query, Object... param) {
        ResultSet resultSet = null;
        try {
            setPrepareStatementAndSetObject(query.getQuery(), param);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            log.error("Query execution failed {}", query);
            log.error("Error Code: {} ", e.getErrorCode());
            log.error("SQL state: {}",  e.getSQLState());
            log.error("Error Message: {}", e.getMessage());
        }
        return resultSet;
    }

    @Override
    public int executeUpdate(IQuery query, Object... param) {
        connect();
        return executeUpdateQuery(query, param);
    }

    @Override
    public int executeUpdateHydraDatabase(IQuery query, Object... param) {
        connectHydraDataBase();
        return executeUpdateQuery(query, param);
    }

    @Override
    public int executeUpdateHydraProxyDatabase(IQuery query, Object... param) {
        connectHydraProxyDataBase();
        return executeUpdateQuery(query, param);
    }

    @Override
    public int executeUpdateQuery(IQuery query, Object... param) {
        try {
            setPrepareStatementAndSetObject(query.getQuery(), param);
            updateValue = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to execute query {}", query);
        }

        return updateValue;
    }

    private void setPrepareStatementAndSetObject(String query, Object[] param) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        int i = 1;
        for (Object value : param) {
            if (value instanceof String[]) {
                preparedStatement.setArray(i, connection.createArrayOf("text", (Object[]) value));
            } else if (value instanceof JSONObject) {
                preparedStatement.setObject(i, value.toString());
            } else {
                preparedStatement.setObject(i, value);
            }
            i++;
        }
    }

    private void connect() {
        connection = DBConnectionManager.getPostgresConnection();
    }

    private void connectAnalyticsDataBase() {
        connection = DBConnectionManager.getConnectionAnalyticsDatabase();
    }

    private void connectHydraDataBase() {
        connection = DBConnectionManager.getConnectionHydraDatabase();
    }

    private void connectHydraProxyDataBase() {
        connection = DBConnectionManager.getConnectionHydraProxyDatabase();
    }
}
