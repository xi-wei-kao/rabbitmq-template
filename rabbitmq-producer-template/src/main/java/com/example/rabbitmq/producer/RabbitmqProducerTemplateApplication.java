package com.example.rabbitmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class, 
		RabbitAutoConfiguration.class
})

public class RabbitmqProducerTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerTemplateApplication.class, args);
	}

}
