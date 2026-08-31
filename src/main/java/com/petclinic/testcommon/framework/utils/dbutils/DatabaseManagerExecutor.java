package com.petclinic.testcommon.framework.utils.dbutils;

import java.sql.ResultSet;

@FunctionalInterface
public interface DatabaseManagerExecutor {
    ResultSet execute();
}
