package com.zheng.pattern;

/**
 * Created by zhenghui on 2018/8/21.
 * 模板模式
 */
public class TemplateModel {
    //入口函数 运行用
    public static void main(String args[]) {
        AbstractClass eatFoodClass = new EatFoodClass();
        eatFoodClass.setRice(true);
        eatFoodClass.TemplateMethod();
    }
}

abstract class AbstractClass {
    private boolean isRice;

    //模板方法
    public void TemplateMethod() {
        eatPorridge();
        //通过钩子方法来确定某步骤是否执行
        if (isRice()) {
            eatRice();
        }
    }

    //基本方法—具体方法  吃米饭
    public void eatRice() {
        //实现
        System.out.println("吃米饭");
    }

    //基本方法—抽象方法 吃粥
    public abstract void eatPorridge();

    //基本方法—钩子方法
    public boolean isRice() {
        return isRice;
    }

    public void setRice(boolean rice) {
        isRice = rice;
    }
}

class EatFoodClass extends AbstractClass {
    @Override
    public void eatPorridge() {
        System.out.println("喝粥");
    }

    @Override
    public void eatRice() {
        super.eatRice();
    }

    @Override
    public boolean isRice() {
        return super.isRice();
    }
}

