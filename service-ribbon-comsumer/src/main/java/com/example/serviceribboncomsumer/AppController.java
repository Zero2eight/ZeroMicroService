package com.example.serviceribboncomsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AppController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/factorial/{n}")
    public int factorial(@PathVariable("n") int n) {
        return restTemplate.getForObject("http://eureka-client-server/factorial/{n}", Integer.class, n);
    }
}
