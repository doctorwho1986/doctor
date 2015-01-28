package com.doctor.mybatis3practice.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class InstantTypeHandler extends BaseTypeHandler<Instant> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Instant parameter, JdbcType jdbcType) throws SQLException {
		ps.setTimestamp(i, Timestamp.from(parameter));
	}

	@Override
	public Instant getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(columnName);
		if (timestamp != null) {
			return timestamp.toInstant();
		}
		return null;
	}

	@Override
	public Instant getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(columnIndex);
		if (timestamp != null) {
			return timestamp.toInstant();
		}
		return null;
	}

	@Override
	public Instant getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp timestamp = cs.getTimestamp(columnIndex);
		if (timestamp != null) {
			return timestamp.toInstant();
		}
		return null;
	}

}
