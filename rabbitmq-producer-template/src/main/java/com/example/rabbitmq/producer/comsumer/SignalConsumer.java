package com.example.rabbitmq.producer.comsumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SignalConsumer {

	@Bean
	public Consumer<String> signalAccepted() {
		return message -> {
			System.out.println("consume message = " + message);
			log.info("MQ接收消息: {}", message);
		};
	}
	
}
