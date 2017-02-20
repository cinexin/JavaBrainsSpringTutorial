/**
 * 
 */
package org.migue.javabrains;

import org.migue.javabrains.dao.HibernateDaoImpl;
import org.migue.javabrains.dao.JdbcDaoImpl;
import org.migue.javabrains.dao.SimpleJdbcDaoImpl;
import org.migue.javabrains.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author migue
 *
 */
public class JdbcDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		JdbcDaoImpl jdbcDao = (JdbcDaoImpl) ctx.getBean("jdbcDaoImpl"); 
		SimpleJdbcDaoImpl simpleJdbcDao = (SimpleJdbcDaoImpl) ctx.getBean("simpleJdbcDaoImpl");
		HibernateDaoImpl hibernateDao = (HibernateDaoImpl) ctx.getBean("hibernateDaoImpl");
		
		Circle circle = jdbcDao.getCircle(1);
		System.out.println("CIRCLE FETCHED: " + circle.getName());
		System.out.println("CIRCLE COUNT: " + jdbcDao.getCircleCount());
		System.out.println("CIRCLE COUNT WITH EXTENDED DAO FROM SPRING: " + simpleJdbcDao.getCircleCount());
		System.out.println("CIRCLE NAME OF ID 1: " + jdbcDao.getCircleName(1));
		System.out.println("CIRCLE NAME FETCHED WITH JDBC SPRING TEMPLATE: " + jdbcDao.getCircleWithJdbcSpring(1).getName());
		System.out.println("CIRCLE COUNT FETCHED WITH HIBERNATE INTEGRATION: " + hibernateDao.getCircleCount());
		//jdbcDao.insertCircle(new Circle(3, "My third circle"));
		//jdbcDao.createTriangleTable();
		// jdbcDao.insertCircleUsingNamedParameters(new Circle(10,"My tenth circle"));
		System.out.println("LIST OF CIRCLES FETCHED WITH JDBC SPRING TEMPLATE: " + jdbcDao.getAllCircles().size());
	}

}
