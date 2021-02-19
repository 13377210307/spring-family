package com.spring.aop.demo.controller;

import com.spring.aop.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: w
 * @Date: 2021/2/17 14:51
 *
 * 需求：在计算登陆时需要消耗的时间
 *
 * 方案：
 * 1：在每个登陆接口中放入计算时长的方法（耦合度较高，不可取，维护也很麻烦）
 * 2：使用AOP将计算时长方法切入
 *
 * spring AOP底层实现
 *
 * 1：jdk动态代理
 * 2：cglib动态代理
 *
 * spring没有默认动态代理方式：代理方式取决于调用对象是否为接口：接口就是jdk动态代理
 */
@RestController
@RequestMapping("/springStudy")
public class SpringStudyController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return this.loginService.login();
    }
}
