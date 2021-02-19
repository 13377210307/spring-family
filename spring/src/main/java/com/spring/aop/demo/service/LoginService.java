package com.spring.aop.demo.service;

import org.springframework.stereotype.Service;

/**
 * @Author: w
 * @Date: 2021/2/17 14:58
 */
@Service
public class LoginService {

    public String login() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "登陆成功";
    }
}
