package com.zheng.pattern;

/**
 * Created by zhenghui on 2017/9/28.
 * 针对接口编程就是针对超类型编程。使用抽象超类可以使用多态。
 */
public class InterfaceTest {

    public static void main(String[] args) {
        //针对实现编程
        Cat cat = new Cat();
        cat.meow();

        //针对接口/超类型编程。使用多态，在运行时才指定具体实现的对象
        Animal animal = new Dog();
        animal.makeSound();
    }

}

interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        bark();
    }

    void bark() {
        System.out.println("汪汪叫");
    }
}

class Cat implements Animal {
    public void makeSound() {
        meow();
    }

    void meow() {
        System.out.println("喵喵叫");
    }
}