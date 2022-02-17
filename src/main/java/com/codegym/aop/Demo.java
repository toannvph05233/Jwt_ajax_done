package com.codegym.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Demo {

    @AfterReturning(pointcut = "within(com.codegym.service.AppUserService)")
    public void success(){
        System.out.println("success");
        System.out.println("success");
        System.out.println("success");
        System.out.println("success");
    }
    @AfterThrowing(pointcut = "within(com.codegym.service.AppUserService)")
    public void error(){
        System.out.println("errer");
        System.out.println("errer");
        System.out.println("errer");
        System.out.println("errer");
    }
}
