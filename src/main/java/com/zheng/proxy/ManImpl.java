package com.zheng.proxy;

/**
 * Created by zhenghui on 2017/12/9.
 */
public class ManImpl implements Man {
    @Override
    public void look(String bookName) {
        System.out.println("look " + bookName);
    }
    @Override
    public String getName(String bookName){
        return bookName;
    }
}
