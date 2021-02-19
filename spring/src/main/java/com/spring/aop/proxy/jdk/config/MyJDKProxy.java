package com.spring.aop.proxy.jdk.config;

import com.spring.aop.proxy.jdk.service.UserService;
import com.spring.aop.proxy.jdk.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: w
 * @Date: 2021/2/18 14:29
 *
 * JDK动态代理对象
 */
public class MyJDKProxy {

    public static void main(String[] args) {
        // 创建接口实现代理对象
        Class[] interfaces = {UserService.class};

        UserService userService = new UserServiceImpl();

        UserService newUserService  = (UserService)Proxy.newProxyInstance(MyJDKProxy.class.getClassLoader(), interfaces, new MyInvocationHandler(userService));

        int add = newUserService.add(1, 2);

        System.out.println(add);

    }


}

// 创建代理对象处理器
class MyInvocationHandler implements InvocationHandler{

    // 1：把代理对象作为参数传递
    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    // 增强逻辑
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("方法之前的增强方法...");

        // 被执行的增强方法
        Object invoke = method.invoke(object, args);

        System.out.println("方法之后的增强方法...");

        return invoke;
    }
}
