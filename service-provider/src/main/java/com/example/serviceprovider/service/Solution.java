package com.example.serviceprovider.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().factorial(5));
    }

    public int minCommonMultiple(int n1, int n2) { //n1和n2的最小公倍数
        int maxDivisor = maxCommonDivisor(n1,n2);
        int minMult = n1*n2/maxDivisor;
        return minMult;
    }

    public int maxCommonDivisor(int n1, int n2) { //n1和n2的最大公约数
        int smaller = Math.min(n1,n2);
        int bigger = Math.max(n1,n2);
        while (bigger % smaller != 0) {
            int temp = smaller;
            smaller = bigger % smaller;
            bigger = temp;
        }
        return smaller;
    }

    public int factorial(int n) {
        int rst = n;
        while (n>1) {
            rst = rst*(--n);
        }
        return rst;
    }

}
