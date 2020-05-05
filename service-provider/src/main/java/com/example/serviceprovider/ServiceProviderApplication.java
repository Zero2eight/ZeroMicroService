package com.example.serviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.serviceprovider","com.example.serviceprovider.service"})
public class ServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProviderApplication.class, args);
	}

}
