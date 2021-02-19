package com.spring.aop.proxy.jdk.service.impl;

import com.spring.aop.proxy.jdk.service.UserService;

/**
 * @Author: w
 * @Date: 2021/2/18 14:27
 */
public class UserServiceImpl implements UserService {


    public int add(int a, int b) {

        System.out.println("add方法执行了");
        return a+b;
    }
}
