package com.example.servicefeign.Hysrixcomsumer;

import com.example.servicefeign.Hysrixcomsumer.service.CustomHystrixCommand;
import com.example.servicefeign.Hysrixcomsumer.service.FeignInterface;
import com.example.servicefeign.Hysrixcomsumer.service.MyService;
import com.example.servicefeign.Hysrixcomsumer.service.RequestCollapser;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class MyController {
    @Autowired
    private MyService ms;

    @Autowired
    private FeignInterface fi;

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

    @RequestMapping(value = "/median")
    public int median(
            @RequestParam(value = "n1", defaultValue = "0") int n1,
            @RequestParam(value = "n2", defaultValue = "0") int n2,
            @RequestParam(value = "n3", defaultValue = "0") int n3) {
        CustomHystrixCommand chc = new CustomHystrixCommand(fi,n1,n2,n3);
        return chc.execute();
    }

    @RequestMapping(value = "/swap/{arr}")
    int[] swap(@PathVariable("arr") int[] arr) throws Exception{
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        RequestCollapser rc = new RequestCollapser(fi,arr);
        Future<int[]> f1 = rc.queue();
        int[] ret = f1.get();
        context.close();
        return ret;
    }
}
