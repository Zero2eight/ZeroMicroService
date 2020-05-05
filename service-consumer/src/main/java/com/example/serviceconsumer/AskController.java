package com.example.serviceconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class AskController {
    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private RestTemplate myRT;

    @RequestMapping(value = "/ask")
    public String ask() {
        return myRT.getForEntity("http://eureka-client-server/hello/{name}", String.class,name).getBody();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
