package com.doctor.mybatis3practice.mapper;

import org.apache.ibatis.annotations.Param;

import com.doctor.mybatis3practice.domain.Blog;

/**
 * @see https://github.com/mybatis/mybatis-3/wiki/FAQ
 *
 * 
 *      What is the difference between #{...} and ${...}?
 * 
 *      MyBatis interprets #{...} as a parameter marker in a JDBC prepared
 *      statement. MyBatis interprets ${...} as string substitution. It is
 *      important to know the difference because parameter markers cannot be
 *      used in certain places in SQL statements. For example, you cannot use a
 *      parameter marker to specify a table name. Given the following code:
 * 
 *      Map<String, Object> parms = new HashMap<String, Object>();
 *      parms.put("table", "foo"); 
 *      parms.put("criteria", 37); 
 *      List<Object> rows = mapper.generalSelect(parms); 
 *      <select id="generalSelect"  parameterType="map">
 *      
 *       select * from ${table} where col1 = #{criteria}
 *       
 *      </select> 
 *      
 *      MyBatis will generate the following prepared statement:
 * 
 *      select * from foo where col1 = ? 
 *      
 *      Important: note that use of ${...} (string substitution) presents a risk 
 *      for SQL injection attacks. Also,
 *      string substitution can be problematical for complex types like dates.
 *      For these reasons, we recommend using the #{...} form whenever possible.
 * 
 * 
 * @author doctor
 *
 * @since 2015年1月22日 下午9:32:17
 */
public interface BlogMapper {
	Blog queryById(Long id);

	int insertBlog(Blog blog);

	int createNewTable(@Param("tableName") String tableName);

	int dropTable(@Param("tableName") String tableName);

	int existTable(String tableName);
}
