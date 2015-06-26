package com.redis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class UserLogger {

	public static final String edp = "execution(* com.redis.controller.*.*(..))";
	
	@Before(edp)
	public void before(JoinPoint call){
		 String className = call.getTarget().getClass().getName();  
	        String methodName = call.getSignature().getName();  
	        System.out.println("【注解-前置通知】:" + className + "类的"   
	                + methodName + "方法开始了"); 
	}
	
	@After(edp)
	public void after(JoinPoint call){
		 String className = call.getTarget().getClass().getName();  
	        String methodName = call.getSignature().getName();  
	        System.out.println("【注解-后置通知】:" + className + "类的"   
	                + methodName + "方法结束了"); 
	}
}
