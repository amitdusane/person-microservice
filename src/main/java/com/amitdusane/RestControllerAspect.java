package com.amitdusane;


import java.util.NoSuchElementException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RestControllerAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CounterService cntService;
	
	@Before("execution(public * com.amitdusane.*Controller.*(..))")
	public void logBeforeRestCall(JoinPoint jp){
		log.debug(":: Before rest call :: " + jp);
	}

	@AfterReturning("execution(public * com.amitdusane.*Controller.*(..))")
	public void logAfterRestCall(JoinPoint jp){
		log.debug(":: After rest call :: " + jp);
	}
	
	@AfterReturning("execution(public * com.amitdusane.*Controller.createPerson*(..))")
	public void logAfterPersonCreated(JoinPoint jp){
		log.debug(":: After person created :: " + jp);
		cntService.increment("com.amitdusane.api.rest.personController.created");
	}
	
	@AfterThrowing(pointcut = "execution(public * com.amitdusane.*Controller.*(..))", throwing = "e")
	public void logAfterExceptionThrown(NoSuchElementException e){
		cntService.increment("com.amitdusane.api.rest.personController.exception");
	}
	
}
