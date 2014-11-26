package com.doctor.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.doctor.enums.IEnumDescription;

public abstract class AbstractIEnumDescriptionHandler extends BaseTypeHandler<IEnumDescription> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, IEnumDescription parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getIndex());
		
	}

	@Override
	public IEnumDescription getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEnumDescription getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEnumDescription getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
