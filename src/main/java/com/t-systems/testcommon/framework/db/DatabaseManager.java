package com.verimi.testcommon.framework.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DatabaseManager {

    int getUpdateValue();

    @Deprecated // use getSql
    PreparedStatement getPreparedStatement();

    ResultSet executeQuery(IQuery query, Object... param);

    ResultSet executeQueryAnalyticsDatabase(IQuery query, Object... param);

    ResultSet executeQueryHydraDatabase(IQuery query, Object... param);

    ResultSet executeQueryHydraProxyDatabase(IQuery query, Object... param);

    int executeUpdate(IQuery query, Object... param);

    int executeUpdateHydraDatabase(IQuery query, Object... param);

    int executeUpdateHydraProxyDatabase(IQuery query, Object... param);

    int executeUpdateQuery(IQuery query, Object... param);


}
