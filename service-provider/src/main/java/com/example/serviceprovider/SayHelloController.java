package com.example.serviceprovider;


import com.example.serviceprovider.service.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
    @RequestMapping(value = "/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "Hello, " + name + "!";
    }

    @Autowired
    private Solution sol;

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
}
