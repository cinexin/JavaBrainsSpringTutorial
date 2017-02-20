/**
 * 
 */
package org.migue.javabrains.service;

import org.migue.javabrains.aspect.MyOwnAspect;
import org.migue.javabrains.model.Circle;

/**
 * @author migue
 * this is a brief demonstration on how we can implement a proxy to make our own implementation of aspects work
 * (see "MyOwnAspect" class and "FactoryService" class)...
 */
public class ShapeServiceProxy extends ShapeService {
	
	@Override
	public Circle getCircle() {
		/* i can implement code here before the actual method is called... */
		new MyOwnAspect().loggingAdvice();
		Circle circle =  super.getCircle();
		/* i can implement code here after the actual method is called... */
		new MyOwnAspect().loggingAdvice();
		return circle;
	}
}
