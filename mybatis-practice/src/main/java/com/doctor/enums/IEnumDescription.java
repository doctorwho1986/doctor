package com.doctor.enums;

/**
 * 为了方便mybatis统一处理enum类型，让其有共同接口
 * @author doctor
 *
 * @since 2014年11月26日 下午10:22:23
 */
public interface IEnumDescription {
	public int getIndex();
	public String getName();
	public String getDescription();
}
