package com.zheng.algorithm;

/**
 * Created by zhenghui on 2018/8/16.
 * 二分查找法
 */
public class BinarySearch {

    /**
     * 从1-20中找出5
     * Math.ceil(double x) : 返回大于或者等于指定表达式的最小整数，即向上取整
     * log1024 = 10 (2ⁿ = 1024，n = 10)
     * @param x   查找值
     * @param min
     * @param max
     */
    public static void search(long x, long min, long max) {
        long original = max;
        long high = 0;
        long low = 0;
        long count = 0;
        while (original != x && original >= min) {
            if (original > x) {
                high = original;
                original = Double.valueOf(Math.ceil(Double.valueOf(original) / 2)).longValue();
                if (original < low) {
                    original = low + Double.valueOf(Math.ceil(Double.valueOf(high - low) / 2)).longValue();
                }
            } else if (original < x) {
                low = original;
                original = low + Double.valueOf(Math.ceil((high - low) / 2)).longValue();
            }
            count++;
            System.out.println("---------->" + count + "：" + original);
        }
        System.out.println("---------->程序结束；计算次数：" + count);
    }

    public static void main(String[] args) {
        //其他地参数用数组 long[]
        BinarySearch.search(65, 1, 4000000000L);
    }
}
