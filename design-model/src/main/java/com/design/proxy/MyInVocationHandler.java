package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: w
 * @Date: 2021/2/26 14:49
 *
 * 代理对象处理类
 */
public class MyInVocationHandler implements InvocationHandler {

    // 代理对象
    private Object object;

    public MyInVocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法切入前...");
        Object invoke = method.invoke(object, args);
        System.out.println(invoke);
        return "";
    }
}
