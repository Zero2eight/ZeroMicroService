package com.example.servicefeign.Hysrixcomsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client-server", fallback = HystrixFallBack.class)
public interface FeignInterface {
    @RequestMapping(value = "hello/{name}", method = RequestMethod.GET)
    String ask(@PathVariable("name") String name);

    @RequestMapping(value = "/cal-max-com-divisor/{n1}/{n2}")
    int calMaxCommonDivisor(@PathVariable("n1") int n1, @PathVariable("n2") int n2);

    @RequestMapping(value = "/median")
    int median(
            @RequestParam(value = "n1", defaultValue = "0") int n1,
            @RequestParam(value = "n2", defaultValue = "0") int n2,
            @RequestParam(value = "n3", defaultValue = "0") int n3);

    @RequestMapping(value = "/swap/{arr}")
    int[] swap(
            @PathVariable("arr") int[] arr);
}

@Component
class HystrixFallBack implements FeignInterface
{
    @Override
    public String ask(String name) {
        return "Can't accept content.";
    }

    @Override
    public int calMaxCommonDivisor(int n1, int n2) {
        return -10;
    }

    @Override
    public int median(int n1, int n2, int n3) {
        return -99;
    }

    @Override
    public int[] swap(int[] arr) {
        return new int[0];
    }
}
