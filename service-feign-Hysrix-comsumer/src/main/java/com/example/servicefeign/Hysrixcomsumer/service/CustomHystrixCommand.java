package com.example.servicefeign.Hysrixcomsumer.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CustomHystrixCommand extends HystrixCommand<Integer> {
    private FeignInterface fi;

    private int n1,n2,n3;

    public CustomHystrixCommand(FeignInterface fi, int n1, int n2, int n3) {
        super(HystrixCommandGroupKey.Factory.asKey("CustomServiceGroup"));
        this.fi = fi;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    @Override
    protected Integer run() {
        return fi.median(n1,n2,n3);
    }

    @Override
    protected Integer getFallback() {
        return -999;
    }
}
