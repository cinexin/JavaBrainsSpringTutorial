/**
 * 
 */
package org.migue.learning.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author migue
 *
 */
public class DisplayNameBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
			System.out.println(">>Here in postProcessAfterInit bean: " + arg1 + "");
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println(">>Here in postProcessBeforeInit bean: " + arg1 + "");
		return arg0;
	}
	
}
