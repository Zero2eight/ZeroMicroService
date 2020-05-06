package com.example.serviceprovider.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class QSortClass {
    public int QSort(int[] arr, int k) {
        return 0;
    }

    public int median(int n1, int n2, int n3) {  //寻找三个数中的中位数
        int[] arr = new int[]{n1,n2,n3};
        Arrays.sort(arr);
        return arr[1];
    }

    public int[] swap(int[] arr, int i1, int i2) {  //将数组arr中的i1和i2位置的元素交换位置
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }
}
