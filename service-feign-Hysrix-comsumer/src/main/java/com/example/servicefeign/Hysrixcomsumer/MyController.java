package com.example.servicefeign.Hysrixcomsumer;

import com.example.servicefeign.Hysrixcomsumer.service.FeignInterface;
import com.example.servicefeign.Hysrixcomsumer.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyService ms;

    @RequestMapping(value = "/cal-min-com-multi/{n1}/{n2}")
    public int cal(@PathVariable("n1") int n1, @PathVariable("n2") int n2) {
        return ms.calMinCommonMultiple(n1, n2);
    }

    @RequestMapping(value = "/hello/{name}")
    public String ask(@PathVariable("name") String name) {
        return ms.ask(name);
    }

    @RequestMapping(value = "/cal-max-com-divisor/{n1}/{n2}")
    public int calMaxCommonDivisor(@PathVariable("n1") int n1, @PathVariable("n2") int n2) {
        try {
            return ms.maxCommonDivisorAsync(n1, n2).get();
        } catch (Exception e) {
            e.printStackTrace();
            return -11;
        }
    }
}
