package com.example.serviceprovider;


import com.example.serviceprovider.service.QSortClass;
import com.example.serviceprovider.service.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
    @RequestMapping(value = "/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "Hello, " + name + "!";
    }

    @Autowired
    private Solution sol;

    @Autowired
    private QSortClass qsl;

    @RequestMapping(value = "/cal-min-com-multi/{n1}/{n2}")
    public int calMinCommonMultiple(@PathVariable("n1") int n1, @PathVariable("n2") int n2) {
        return sol.minCommonMultiple(n1,n2);
    }

    @RequestMapping(value = "/cal-max-com-divisor/{n1}/{n2}")
    public int calMaxCommonDivisor(@PathVariable("n1") int n1, @PathVariable("n2") int n2) {
        return sol.maxCommonDivisor(n1,n2);
    }

    @RequestMapping(value = "/factorial/{n}")
    public int factorial(@PathVariable("n") int n) {
        return sol.factorial(n);
    }

    @RequestMapping(value = "/median")
    public int median(
            @RequestParam(value = "n1", defaultValue = "0") int n1,
            @RequestParam(value = "n2", defaultValue = "0") int n2,
            @RequestParam(value = "n3", defaultValue = "0") int n3) {
        return qsl.median(n1,n2,n3);
    }

    @RequestMapping(value = "/swap/{arr}")
    public int[] swap(
            @PathVariable("arr") int[] arr,
            @RequestParam(value = "i1", defaultValue = "0") int i1,
            @RequestParam(value = "i2", defaultValue = "-1") int i2) {
        if (i2 == -1) i2 = arr.length-1;
        return qsl.swap(arr,i1,i2);
    }

}
