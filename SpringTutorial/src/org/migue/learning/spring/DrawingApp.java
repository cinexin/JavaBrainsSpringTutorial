/**
 * 
 */
package org.migue.learning.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author migue
 *
 */
public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Triangle triangle = new Triangle();
		//BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		
		AbstractApplicationContext context = new  ClassPathXmlApplicationContext("spring.xml");
		context.registerShutdownHook();
		Triangle triangle = (Triangle)  context.getBean("triangle1");
		
		System.out.println("CALL TO DRAW METHOD.....");
		triangle.draw();
	}

}
