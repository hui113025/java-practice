package com.zheng.pattern;

/**
 * Created by zhenghui on 2017/9/28.
 * 鸭子特征是会叫、会游泳、不同的外观、不同的行为能力
 * 一般鸭子不会飞呱呱叫
 * 橡皮鸭不会飞会吱吱叫
 * 诱饵鸭，不会飞不会叫
 * 飞和叫，是重叠又不重复，把飞和叫的行为设定为特征，使用继承就行复用
 * 当鸭子的种类不断增多时就有些不适用，不是所有牛奶都叫特仑苏，此时我们可以试着使用接口
 * 继承好处：复用
 */
public class ExtendsTestDuck {

    public static void main(String[] args){

    }

}

/**
 * 鸭子超类
 */
class DuckExtends {

    /**
     * 呱呱叫
     */
    void quack(){

    }

    /**
     * 游泳
     */
    void swim(){

    }

    /**
     * 外观 抽象
     */
    void display(){

    }

    /**
     * 会飞
     */
    void fly(){

    }

}

/**
 * 诱饵鸭，不会飞不会叫
 */
class DecoyDuckExtends extends DuckExtends {

    @Override
    void quack() {
        // 覆盖，变成什么事情都不做
    }

    @Override
    void display() {
        // 外观是诱饵鸭
    }

    @Override
    void fly() {
        // 覆盖，变成什么事情都不做
    }

}

/**
 * 绿头鸭子
 */
class MallardDuckExtends {

    void display() {
        //外观是绿头
    }
}

/**
 * 红头鸭子
 */
class RedHeadDuckExtends {

    void display() {
        //外观是红头
    }
}

/**
 * 橡皮鸭，不会飞会吱吱叫
 */
class RubberDuck extends DuckExtends {

    @Override
    void quack() {
        // 覆盖成吱吱叫
    }

    @Override
    void display() {
        // 外观是橡皮鸭
    }

    @Override
    void fly() {
        // 覆盖，变成什么事情都不做
    }

}
