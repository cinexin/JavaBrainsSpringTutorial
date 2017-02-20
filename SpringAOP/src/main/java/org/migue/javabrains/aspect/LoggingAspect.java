package org.migue.javabrains.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	/* Example of applying the method to a specific Class...
	@Before ("execution(public String org.migue.javabrains.model.Circle.getName())") */
	
	/* This would apply to all methods with the given signature...
	@Before ("execution(public String getName())") */
	
	/* This applies to all "getters" methods, we just add wildcard expressions...
	 * a rule for the arguments in methods: "*" is for "one or more arguments" and ".." is for "zero or more arguments"*/

	/* this is the way in which we can define expressions for execution(expr)...*/
	@Pointcut("execution(public * get*(..))")
	public void allGetters() { 
		/* this is a dummy method for holding pointcut expressions, so they can be referenced later in annotations @Before etc */
	}
	
	/* @Pointcut("execution(* * org.migue.javabrains.model.Circle.*(..))") 
	 we can specify the same pointcut described in previous line in this more readable way... 
	note that the argument of within are always classes */
	@Pointcut("within(org.migue.javabrains.model.Circle)")
	public void allCircleMethods() { }
	
	/* this pointcut is used for specifying methods that match the arguments given...*/
	@Pointcut("args(org.migue.javabrains.model.Circle)")
	public void allMethodsWithCircleArgs() {}
	
	/* this combination of pointcuts allows us to write modular pointcuts...
	 * for instance, in this case the loggingAdvice should run only for getters of the Circle class
	 
	@Before ("allGetters() && allCircleMethods()")*/
	@Before("allCircleMethods()")
	public void loggingAdvice(JoinPoint joinPoint)  {
		/* the joinPoint argument gives some information of the method that raises me */
		System.out.println("Advice run.");
		//  joinPoint.getTarget gives us a reference to the object that triggered me...
		System.out.println(joinPoint.getTarget());
	}
	
	/* note that the @After advice is always executed after the method is called, even if an exception has been thrown... 
	@After("args(name)")*/
	/* this @AfterReturning only is triggered if the method that causes the execution finishes successfully
	 * "returnString" is the object returned...*/
	@AfterReturning(pointcut="args(name)", returning="returnString")
	public void stringArgumentMethods(String name, String returnString) {
		System.out.println("A method that takes string arguments has been called. The value is " + name + " and the output value is " + returnString);
	}
	
	/* this advice is triggered is an exception is raised by the method... */
	@AfterThrowing(pointcut="args(name)", throwing="ex")
	public void raisingExceptionMethods(String name, RuntimeException ex) {
		System.out.println("An exception has been thrown, the exception is: " + ex);
	}
	
	@Before ("allGetters()")
	public void secondAdvice()  {
		System.out.println("Second Advice executed. ");
	}
	

	/* the @Around tag is for executing before and after a method...*/
	/* note that the @annotaion means this will apply to all methods that are tagged with @Lagabble...*/
	@Around("@annotation(org.migue.javabrains.aspect.Loggable)")
	public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		
		Object returnedObject = null;
		try {
			/* in this part we write the code that runs BEFORE the method is executed...*/
			System.out.println("Before advice in myAroundAdvice().");
			
			
			/* in this line we actually execute the method that raises the pointcut...*/
			returnedObject = proceedingJoinPoint.proceed();

			
			/* in this part we write the code that runs AFTER the method is executed...*/
			System.out.println("After execution in myAroundAdvice()...");

		} catch (Throwable e) {
			System.out.println("After throwing in myAroundAdvice()...");
			e.printStackTrace();
		}
		
		System.out.println("After finally in myAroundAdvice()...");
		
		return returnedObject;
	}

}
