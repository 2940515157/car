package com.xmz.common.serviceBase.auth.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
@SuppressWarnings({"unused"})
public class TokenAspect {

    @Resource
    HttpServletRequest request;

    @Pointcut("@annotation(com.xmz.common.serviceBase.auth.annotation.Token)")
    public void annotationPointcut(){

    }

    @Around("annotationPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        return joinPoint.proceed();
    }

}
