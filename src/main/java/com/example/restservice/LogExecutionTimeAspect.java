package com.example.restservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * LogExecutionTimeAspect implemented:
 * logBeforeMethod
 * logExecutionTime
 *
 * @author Skyhunter
 * @date 16.02.2021
 */
@Component
@Aspect
public class LogExecutionTimeAspect {

    private static Logger LOG = LogManager.getLogger(LogExecutionTimeAspect.class);

    @Before("@annotation(com.example.restservice.LogExecutionTime)")
    public void logBeforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        String methodParams = Arrays.toString(jp.getArgs());

        LOG.info("=======> Выполнение метода: {}", methodName);
        LOG.info("Параметры метода: {}", methodParams);
    }

    @Around("@annotation(com.example.restservice.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        LOG.info("{} выполнен за: {} мс", joinPoint.getSignature(), executionTime);
        LOG.info("<======= Результат: {}", proceed);
        return proceed;
    }
}
