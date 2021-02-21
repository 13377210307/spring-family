package com.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: w
 * @Date: 2021/2/21 11:59
 *
 * 消息丢失问题
 */
@Configuration
public class LoseConfiguration {

    // 定义队列
    @Bean
    public Queue loseQueue() {
        return new Queue("loseQueue");
    }

    // 定义交换机
    @Bean
    public Exchange loseExchange() {
       return new DirectExchange("loseDirectExchange");
    }

    // 定义绑定
    @Bean
    public Binding loseBinding() {
        return BindingBuilder.bind(loseQueue()).to(loseExchange()).with("lose").noargs();
    }
}
