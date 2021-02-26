package com.spring.aop.demo.service;

import org.springframework.stereotype.Service;

/**
 * @Author: w
 * @Date: 2021/2/25 18:08
 */
@Service
public class GetDataService {

    public String getData() {
        return "切入方法返回值...";
    }
}
