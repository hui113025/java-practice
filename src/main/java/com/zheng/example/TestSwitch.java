package com.zheng.example;

public class TestSwitch {

    public static void main(String[] args) {
        char c = 0;
        int i=9;
        //从上到下支持,结果是default,zero
        switch (i) {
            default:
                System.out.println("default");
                //此处无break中断继续执行
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                break;
        }
    }
}
