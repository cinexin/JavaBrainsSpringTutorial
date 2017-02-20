/**
 * 
 */
package org.migue.javabrains.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * @author migue
 * this class shows us how to extend the built-in JDBC DAO's 
 * of Spring framework to create our own DAO's in a more
 * optimized way....
 * PS: in this case, we use the simpleJDBCTemplate, there
 *  are classes for NamedParameterJDBCTemplate and JDBCTemplate too
 *  PS2: view "spring.xml" to find out how the dataSource member variable
 *  is bounded (it's inherited from the parent class)
 */
@SuppressWarnings("deprecation")
public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {

	
	
	public int getCircleCount() {
		String sql = "select count(*) from circle";
		
		return this.getJdbcTemplate().queryForInt(sql);
	}

}
