/**
 * 
 */
package org.migue.javabrains.service;

import org.migue.javabrains.model.Circle;
import org.migue.javabrains.model.Triangle;

/**
 * @author migue
 * this class is a brief demonstration on how could we implement a little Factory of beans...
 *
 */
public class FactoryService {
	
	public Object getBean(String beanType) {
		
		if (beanType.equalsIgnoreCase("shapeService"))
			return new ShapeServiceProxy();
		
		if (beanType.equalsIgnoreCase("circle"))
			return new Circle();

		if (beanType.equalsIgnoreCase("triangle"))
			return new Triangle();
		
		return null;
	}
}
