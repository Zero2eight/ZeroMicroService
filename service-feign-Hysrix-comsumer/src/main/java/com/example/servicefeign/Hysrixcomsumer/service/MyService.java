package com.example.servicefeign.Hysrixcomsumer.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class MyService {
    @Autowired
    private RestTemplate rt;

    @Autowired
    private FeignInterface fi;

    @HystrixCommand(fallbackMethod = "fallbackcal") //单纯使用hystrix处理雪崩
    public int calMinCommonMultiple(int n1, int n2) {
        return rt.getForObject("http://eureka-client-server/cal-min-com-multi/{n1}/{n2}", Integer.class, n1, n2);
    }

    public int fallbackcal(int n1, int n2) {
        return -1;
    }

    public String ask(String name) {
        return fi.ask(name);
    }

    @HystrixCommand(fallbackMethod = "fallbackcalasync")  //使用异步方式使用Hystrix
    public Future<Integer> maxCommonDivisorAsync(int n1, int n2) {
        return new AsyncResult<Integer>() {
            @Override
            public Integer invoke() {
                return fi.calMaxCommonDivisor(n1, n2);
            }
        };
    }

    @HystrixCommand
    public Future<Integer> fallbackcalasync(int n1, int n2) {
        return new AsyncResult<Integer>() {
            @Override
            public Integer invoke() {
                return -1;
            }
        };
    }

}
