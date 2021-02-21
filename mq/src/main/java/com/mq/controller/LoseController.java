package com.mq.controller;

import com.mq.service.LoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: w
 * @Date: 2021/2/21 12:11
 */
@RestController
@RequestMapping("/lose")
public class LoseController {

    @Autowired
    private LoseService loseService;

    @GetMapping("/test")
    public String loseTest() {
        this.loseService.sendMessage();
        return "发送成功";
    }
}
