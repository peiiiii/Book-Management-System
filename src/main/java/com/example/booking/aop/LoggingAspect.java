package com.example.booking.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.booking.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.booking.service.*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        logger.info("After method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.example.booking.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method executed successfully: " + joinPoint.getSignature().toShortString());
        logger.info("Method returned: " + result);
    }


    @AfterThrowing(value = "execution(* com.example.booking.service.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Method threw exception: " + error);
    }
}