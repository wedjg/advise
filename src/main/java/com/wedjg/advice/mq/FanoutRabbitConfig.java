package com.wedjg.advice.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * 订阅类MQ的配置
 * @author wedjg
 * @date 2019-05-21
 */
@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue mailMessage() {
        return new Queue("fanout.mail");
    }

    @Bean
    public Queue testMessage() {
        return new Queue("fanout.test");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeMail(Queue mailMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(mailMessage).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeTest(Queue testMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(testMessage).to(fanoutExchange);
    }
}
