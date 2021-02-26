package com.spring.aop.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: w
 * @Date: 2021/2/25 18:11
 */
@Aspect
@Component
public class GetDataAspectConfig {

    /**
     * 配置需要切入的表达式：只对getData方法进行切入
     */
    @Pointcut("execution(* com.spring.aop.demo.service.GetDataService.getData())")
    public void pointCut() {

    }

    /**
     * 增强方式
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        long end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end - start));
        return result;
    }

}
