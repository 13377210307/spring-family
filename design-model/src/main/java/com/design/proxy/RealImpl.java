package com.design.proxy;

/**
 * @Author: w
 * @Date: 2021/2/26 14:56
 */
public class RealImpl implements Real{

    @Override
    public String show() {
        return "被代理后的输出";
    }
}
