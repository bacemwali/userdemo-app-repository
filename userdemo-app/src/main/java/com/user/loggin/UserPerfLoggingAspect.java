package com.user.loggin;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserPerfLoggingAspect {
	
	//@Around("@annotation(com.userdemo.loggin.UserDemoLog)")
	@Around("@annotation(UserPerfLog)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    	
        long start = System.currentTimeMillis();
        System.out.println("------------ "+joinPoint.getSignature() + " is starting : " + start + "ms ------------ ");
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis(); 
        long executionTime = end - start;
        System.out.println("------------ " + joinPoint.getSignature() + " is ending : " + end + "ms ------------ ");
        System.out.println("------------ " + joinPoint.getSignature() + " executed in " + executionTime + "ms ------------ ");
        
        return proceed;
    }
}