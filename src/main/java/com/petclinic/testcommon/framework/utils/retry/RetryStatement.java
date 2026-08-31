package com.petclinic.testcommon.framework.utils.retry;

import java.sql.SQLException;

@FunctionalInterface
public interface RetryStatement {
    boolean evaluate() throws SQLException;
}
