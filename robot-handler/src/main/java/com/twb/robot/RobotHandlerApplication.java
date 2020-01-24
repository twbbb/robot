package com.twb.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RobotHandlerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RobotHandlerApplication.class, args);
	}
}
