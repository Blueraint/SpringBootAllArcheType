package com.spring.SpringbootAllArchetype.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AopHandler {
    @Around("execution(* com.spring.SpringbootAllArchetype.controller..*Controller.*(..))")
    public Object logging(ProceedingJoinPoint jointPoint) throws Throwable {
        long startTime = System.nanoTime();
        log.debug(" # Time Before : " + jointPoint.getSignature().getDeclaringTypeName() + " / " + jointPoint.getSignature().getName());

        Object result = jointPoint.proceed();

        long endTime = System.nanoTime();
        double elaspedTime = (endTime - startTime)/ 1000000000.0;

        log.debug(" # Time After : " + jointPoint.getSignature().getDeclaringTypeName() + " / " + jointPoint.getSignature().getName() + " : -> " + elaspedTime);

        return result;
    }
}
