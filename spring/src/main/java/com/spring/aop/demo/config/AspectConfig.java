package com.spring.aop.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: w
 * @Date: 2021/2/17 14:57
 *
 * 切面配置
 */
@Aspect
@Component
public class AspectConfig {

    /**
     * 配置需要切入的表达式：该service下所有的类、方法及所在的参数都会被这个切点拦截到
     */
    @Pointcut("execution(* com.spring.aop.demo.service.*.*(..)))")
    public void pointcut() {

    }

    /**
     * 增强方式：环绕增强
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        long end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end - start));
        return result;
    }
}
