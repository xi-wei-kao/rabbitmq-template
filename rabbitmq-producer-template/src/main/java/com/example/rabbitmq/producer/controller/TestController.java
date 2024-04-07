package com.example.rabbitmq.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@RestController
public class TestController {
	
	private final static String CHANNEL_NAME = "acceptSignal-out-0";
	private final static String BINDING_NAME = "signal-accepted"; 
	
	private final static String CHANNEL_NAME2 = "signalAccepted-in-0";

	@Autowired
	private StreamBridge streamBridge;
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@SuperBuilder
	public static class TestDTO {
		private String id;
		private String name;
	}
	
	@GetMapping(path = "/test1")
	public String test1() {
		try {
			TestDTO dto = TestDTO.builder()
					.id("001")
					.name("name")
					.build();
			streamBridge.send(CHANNEL_NAME2, MessageBuilder.withPayload(dto).build());
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		return "OK";
	}
}
