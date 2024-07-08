package com.andres.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("GreetingServicePointcuts.greetingFooAspectPointCut()") //aca va el punto de corte
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    @After("GreetingServicePointcuts.greetingFooAspectPointCut()") // al final de todo
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues: " + method + " con los argumentos " + args);
    }

    @AfterReturning("GreetingServicePointcuts.greetingFooAspectPointCut()") // despues de retornar
    public void loggerAfterReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de retornar: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("GreetingServicePointcuts.greetingFooAspectPointCut()") // con una excepcion
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de lanzar la excepcion: " + method + " con los argumentos " + args);
    }

    @Around("GreetingServicePointcuts.greetingFooAspectPointCut())")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable { // Mientras se esta procesando,
                                                                                 // envuelve a los demas
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {

            logger.info("AROUND EL METODO: " + method + "() con los parametros " + args);
            result = joinPoint.proceed();
            logger.info("AROUND EL METODO: " + method + "() retorna el resultado: " + result);
            return result;
        } catch (Throwable e) {
            logger.info("AROUND ERROR EN LA LLAMADA DEL METODO: " + method + "()");
            throw e;
        }
    }
}
