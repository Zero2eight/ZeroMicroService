package com.example.serviceconsumerfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eureka-client-server")
@RequestMapping("/hello")
public interface FeignInterface {
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    String ask(@PathVariable("name") String name);
}
