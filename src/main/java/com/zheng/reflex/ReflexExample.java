package com.zheng.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhenghui on 2017/12/7.
 */
public class ReflexExample {

    private Integer id;
    private String name;

    public ReflexExample(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args){
        try {
            Class c1 = Class.forName("com.zheng.reflex.ReflexExample");
            Class c2 = ReflexExample.class;
            ReflexExample reflexExample = new ReflexExample(1,"张三");
            Class c3 = reflexExample.getClass();

            Class c = c3;
            Field[] declaredFields = c.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Field df = declaredFields[i];
                Object value = df.get(reflexExample);
                String fieldName = df.getName();
                String fieldValue = String.valueOf(value);
                Boolean isInstanceOf = value instanceof Integer;
                System.out.println("name："+fieldName+" value："+fieldValue+" 类型："+ isInstanceOf);
            }

            Method[] Methods = c.getDeclaredMethods();
            for(Method method : Methods){
                if(method.getModifiers() == 1 && !method.isVarArgs() && !"void".equals(method.getReturnType().getName())){
                    System.out.println("method："+method.getName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
