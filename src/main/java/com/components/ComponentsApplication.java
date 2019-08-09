package com.components;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;



/**
 * @author suyin
 */
@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
public class ComponentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentsApplication.class, args);
	}
}
