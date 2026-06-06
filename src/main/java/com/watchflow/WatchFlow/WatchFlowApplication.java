package com.watchflow.watchflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WatchFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchFlowApplication.class, args);
	}
}
