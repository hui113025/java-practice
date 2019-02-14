package com.zheng.example;

import com.alibaba.fastjson.JSONObject;

/**
 * 冒泡排序、选择排序
 */
public class TestSort {
    public static void main(String[] args) {
        int[] a = {1,4,2};
//        bubbleSort(a);
        selectSort(a);
        Double b = 1.0;
        System.out.println(">>>>>>>>>>>>>>>"+ JSONObject.toJSON(a));
    }

    //冒泡排序
    static void bubbleSort(int[] a){
        int temp = 0;
        for(int i=0; i< a.length-1; i++){
            boolean flag = false;
            for(int j=0;j< a.length-i-1;j++){
                //后一个大于前一个
                if(a[j+1] < a[j]){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if(flag){
                break;
            }
        }
    }

    //选择排序
    static void selectSort(int[] a){
        int temp = 0;
        for(int i=0;i< a.length-1;i++){
            //最小值坐标
            int minIndex = i;
            for(int j=i+1;j<a.length; j++){
                if(a[minIndex] > a[j]){
                    minIndex = j;
                }
            }
            if(i != minIndex){
                temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }

    //插入排序
    static void insertSort(int[] a){
        int temp = 0;
        for (int i = 0; i < a.length-1; i++) {

        }
    }


}
