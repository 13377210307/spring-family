package com.mq.service.impl;

import com.mq.service.LoseService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: w
 * @Date: 2021/2/21 12:09
 */
@Service
public class LoseServiceImpl implements LoseService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage() {
        this.rabbitTemplate.convertAndSend("loseDirectExchange","lose","你好");
    }
}
