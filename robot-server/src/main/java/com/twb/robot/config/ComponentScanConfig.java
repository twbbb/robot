package com.twb.robot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.twb.robot.common.*"})
public class ComponentScanConfig {
	
}
