package com.twb.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RobotServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RobotServerApplication.class, args);
	}
}
