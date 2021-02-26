package com.design.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author: w
 * @Date: 2021/2/26 14:49
 *
 * JDK动态代理模式
 */
public class JDKProxyModel {

    public static void main(String[] args) {
        // 将被代理对象作为参数传入代理对象处理类
        Real real = new RealImpl();
        // 创建代理对象：参数一：类加载器；参数二：被代理对象的接口；参数三：代理对象处理器
        Real real1 = (Real)Proxy.newProxyInstance(JDKProxyModel.class.getClassLoader(),new Class[] {Real.class},new MyInVocationHandler(real));
        real1.show();
    }
}
