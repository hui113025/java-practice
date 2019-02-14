package com.zheng.example;

/**
 * jvm 对象、堆、栈、线程栈、变量、对象引用
 * https://www.cnblogs.com/dingyingsi/p/3760447.html
 */
public class TestObject {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        TestConfig configA = new TestConfig();
        configA.setId(10);
        TestConfig configB = configA;
        System.out.println(configA.getId());
        configB.setId(20);

        System.out.println(configB.getId());
        System.out.println(configA.hashCode());
        System.out.println(configB.hashCode());
    }
}

class TestConfig {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
