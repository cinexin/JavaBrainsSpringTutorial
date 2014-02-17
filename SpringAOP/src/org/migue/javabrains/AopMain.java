/**
 * 
 */
package org.migue.javabrains;

import org.migue.javabrains.service.FactoryService;
import org.migue.javabrains.service.ShapeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author migue
 *
 */


public class AopMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		//ShapeService shapeService = ctx.getBean("shapeService", ShapeService.class);

		//shapeService.getCircle().setNameAndReturn("my Circle");
		
		FactoryService factoryService = new FactoryService();
		ShapeService shapeService =(ShapeService)  factoryService.getBean("ShapeService");
		shapeService.getCircle();
		//System.out.println("CIRCLE NAME: " + shapeService.getCircle().getName());
		//System.out.println("TRIANGLE NAME: " + shapeService.getTriangle().getName());

	}

}
