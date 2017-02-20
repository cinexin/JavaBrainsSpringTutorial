/**
 * 
 */
package org.migue.javabrains.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.migue.javabrains.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

/**
 * @author migue
 *  
 *
 */

@Component
public class JdbcDaoImpl {
	


	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	@Autowired
	private DataSource dataSource;
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/* this is just an example of using raw JDBC to interact with database...*/
	public Circle getCircle(int circleId) {
		
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from Circle where id = ?");
			ps.setInt(1,circleId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Circle(circleId, rs.getString("name"));
			}
			
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	/* this method show us how an example of how to use the JDBCTemplate class 
	 * of the JDBC Spring library to interact with the database...*/
	public int getCircleCount() {
		String sql = "select count(*) from circle";
		
		return jdbcTemplate.queryForInt(sql);
	}
	
	public String getCircleName(int circleId) {
		String sql = "select name from circle where id=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {circleId} ,String.class);
	}
	
	/* this is an example of how we can get our custom objects
	 * with the queryForObject method by using the RowMapper object...
	 */
	public Circle getCircleWithJdbcSpring(int circleId) {
		String sql = "select * from circle where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId}, new CircleMapper());
	}
	
	public List<Circle> getAllCircles() {
		String sql = "select * from circle";
		return jdbcTemplate.query(sql,new CircleMapper());
	}
	
	/* this is an example of how to insert an object in the database using jdbcTemplate of Spring...*/
	public void insertCircle(Circle circle) {
		String sql = "insert into circle(id, name) values (?,?)";
		jdbcTemplate.update(sql, new Object [] {circle.getId(), circle.getName()});
	}

	/* this is an example of how to insert an object in the database using jdbcTemplate of Spring,
	 * in this case using named parameters instead of "?" in the SQL query...*/
	public void insertCircleUsingNamedParameters(Circle circle) {
		// we define the parameters by using ":" before the parameter name... 
		String sql = "insert into circle(id, name) values (:id,:name)";
		// we'll use a hash map to indicate the name and value of the parameters we must supply to the query...
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", circle.getId()).addValue("name", circle.getName());
		// we'll use the class NamedParameterJdbcTemplate to run named parameters queries...
		namedParameterJdbcTemplate.update(sql, namedParameters);
		
		
	}
	/* this is an example of how we can use jdbcTemplate of Spring to
	 * create DDL sentences...
	 */
	
	public void createTriangleTable() {
		String sql = "create table Triangle (ID integer, NAME varchar(25) )";
		jdbcTemplate.execute(sql);
	}
	
	/* this is an inner class that we will use to
	 * map the columns of circle with the attributes of
	 * the Circle class
	 */
	private static final class CircleMapper implements RowMapper<Circle> {

		@Override
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("NAME"));
			return circle;
		}
		
	}
}
