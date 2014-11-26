package com.doctor.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;


import com.doctor.enums.IEnumDescription;
import com.doctor.util.IEnumDescriptionUtil;

/**
 * mybatis对enum类型统一处理抽象handler，利用注解获得子类实际类型，
 * 子类handler只需要简单继承此类，添加注解 即可
 * @author doctor
 *
 * @since 2014年11月26日 下午10:24:19
 */
public abstract class AbstractIEnumDescriptionHandler extends BaseTypeHandler<IEnumDescription> {
	private Class<IEnumDescription> type;
	
	@SuppressWarnings("unchecked")
	public AbstractIEnumDescriptionHandler(){
		MappedTypes annotation = getClass().getAnnotation(MappedTypes.class);
		if (annotation == null) {
			throw new RuntimeException("typehandler:" + getClass().getName()+ " MappedTypes annotation value is empty ");
		}
		
		type =   (Class<IEnumDescription>) annotation.value()[0];
		
	}
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, IEnumDescription parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getIndex());
		
	}

	@Override
	public IEnumDescription getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int index = rs.getInt(columnName);
		return IEnumDescriptionUtil.of(type, index);
	}

	@Override
	public IEnumDescription getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int index = rs.getInt(columnIndex);
		return IEnumDescriptionUtil.of(type, index);
	}

	@Override
	public IEnumDescription getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int index = cs.getInt(columnIndex);
		return IEnumDescriptionUtil.of(type, index);
	}
	
}
