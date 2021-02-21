package com.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: w
 * @Date: 2021/2/21 12:06
 */
@Configuration
public class LoseQueueListener {

    @RabbitListener(queues = "loseQueue")
    public void receiveLoseMessage(String message) {
        System.out.println(message);
    }
}
