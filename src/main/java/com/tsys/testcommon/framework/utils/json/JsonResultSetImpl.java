package com.tsys.testcommon.framework.utils.json;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonResultSetImpl implements ResultSet {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final List<Map<String, Object>> result;
    private Map<String, Object> currentRow;
    private int position = -1;

    @SneakyThrows
    public JsonResultSetImpl(String json) {
        result = OBJECT_MAPPER.readValue(json, List.class);
    }

    @Override
    public boolean next() throws SQLException {
        boolean hasNext = ++position < result.size();
        if (hasNext) {
            currentRow = result.get(position);
        }
        return hasNext;
    }

    @Override
    public void close() throws SQLException {
        //it's not a stream so nothing's required
    }

    @Override
    public boolean wasNull() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return (String) currentRow.get(columnLabel);
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        Object result = currentRow.get(columnLabel);
        return result != null && (boolean) result;
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        Object result = currentRow.get(columnLabel);
        return result == null ? 0 : (byte) result;
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        Object result = currentRow.get(columnLabel);
        return result == null ? 0 : (short) result;
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        Object result = currentRow.get(columnLabel);
        return result == null ? 0 : (int) result;
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        Object result = currentRow.get(columnLabel);
        if (result == null) {
            return 0L;
        }
        if (result instanceof Long) {
            return (long) result;
        } else {
            return Long.parseLong(result.toString());
        }
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        Object result = currentRow.get(columnLabel);
        return result == null ? 0f : (float) result;
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        Object result = currentRow.get(columnLabel);
        return result == null ? 0d : (double) result;
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        Object result = currentRow.get(columnLabel);
        return result == null ? BigDecimal.ZERO : BigDecimal.valueOf((double) result);
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public String getCursorName() throws SQLException {
        throw new NotImplementedException();
    }

    @SneakyThrows
    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        return currentRow.get(columnLabel);
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return position < 0;
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return position >= result.size();
    }

    @Override
    public boolean isFirst() throws SQLException {
        return position == 0;
    }

    @Override
    public boolean isLast() throws SQLException {
        return position == result.size() - 1;
    }

    @Override
    public void beforeFirst() throws SQLException {
        position = -1;
    }

    @Override
    public void afterLast() throws SQLException {
        position = result.size();
    }

    @Override
    public boolean first() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean last() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getRow() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean previous() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getType() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getConcurrency() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean rowInserted() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {

    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {

    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void insertRow() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateRow() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void deleteRow() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void refreshRow() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Statement getStatement() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new NotImplementedException();
    }
}
