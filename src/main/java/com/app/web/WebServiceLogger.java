package com.app.web;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class WebServiceLogger {
    protected Logger logger;

    @Around("@annotation(com.app.web.Loggable)")
    public void myAdvice(ProceedingJoinPoint proceedingJoinPoint){
        long startTime = System.currentTimeMillis();
        logger = Logger.getLogger(proceedingJoinPoint.getTarget().getClass());
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Method invoked: " + methodName);
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error("Error in method " + methodName + ". " + e.getMessage());
            e.printStackTrace();
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        logger.info("Method finished: " + methodName + " (" + elapsedTime + ")");
    }

}
