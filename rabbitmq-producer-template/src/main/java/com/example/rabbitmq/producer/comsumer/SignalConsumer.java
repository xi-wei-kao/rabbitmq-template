package com.example.rabbitmq.producer.comsumer;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SignalConsumer {

	@Bean
	public Consumer<Message<List<String>>> acceptSignal() {
		return message -> {
			System.out.println("consume message = " + message);
			log.info("MQ接收消息: {}", message);
			
			// [Step]: 取出通道、遞送標籤(辨識符)
			Channel channel = message.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
			Long deliveryTag = message.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
			
			// [Step]: 執行消費訊息主程序
			// xxxOooService.process(message.getPayload());
			
			// [Step]: 確認訊息處理完成, ACK message
			try {
				// CASE: 確認訊息
				channel.basicAck(deliveryTag, true);
				
				// CASE: 拒絕訊息
				// channel.basicReject(deliveryTag, false);
			} catch (IOException error) {
				log.error("ack message fail, (message, error) ==> ({}, {})",
						message.getPayload(),
						error);
			}
			
		};
	}
	
}
