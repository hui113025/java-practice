package com.zheng.design;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by zhenghui on 2017/9/29.
 * 观察者模式
 * 在对象之间定义一对多的依赖，当一个对象改变状态，依赖它的对象都会收到通知，并自动更新。例如：杂志订阅与取消、猎头与求职者。
 */
public class ObserverModel {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay conditionDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);

        WeatherDataForObservable weatherObservable = new WeatherDataForObservable();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherObservable);
        weatherObservable.setMeasurements(90, 75, 40.4f);
    }

}

/**
 * 主题
 */
interface Subject {
    void registerObserver(Observer o); //注册

    void removeObserver(Observer o); //注销

    void notifyObservers(); //通知
}

/**
 * 观察者
 */
interface Observer {
    void update(float temp, float humidity, float pressure); //一般模式
}

/**
 * 布告板
 */
interface DisplayElement {
    void display();
}

/**
 * 气象站主题
 */
class WeatherData implements Subject {
    private ArrayList<Observer> observers; //观察者
    private float temperature; //温度
    private float humidity; //湿度
    private float pressure; //气压

    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    //测量变化
    void measurementsChanged() {
        notifyObservers();
    }

    //设定天气变化时向消费者发消息
    void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
    //WeatherData的其他方法
}

/**
 * 气象站主题 用内置方法
 * Observable java.util内置类，缺点是扩展性不强
 * setChanged() protected 受保护，提供的方法有限，只能继承
 */
class WeatherDataForObservable extends Observable {
    private float temperature; //温度
    private float humidity; //湿度
    private float pressure; //气压

    public WeatherDataForObservable() {
    }

    //测量变化
    void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    //设定天气变化时向消费者发消息
    void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
    //WeatherData2的其他方法
}

/**
 * 目前状况布告板
 * 显示温度、湿度
 */
class CurrentConditionDisplay implements Observer, DisplayElement {
    private float temperature; //温度
    private float humidity; //湿度
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temp, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void update(Observer obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    public void display() {
        System.out.println("自定义方法：" + temperature + "℃ and " + humidity + "%RH");
    }
}

/**
 * 统计布告板
 */
class StatisticsDisplay implements java.util.Observer, DisplayElement {
    Observable observable;
    private float temperature; //温度
    private float humidity; //湿度
    private float pressure; //气压

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherDataForObservable) {
            WeatherDataForObservable weatherData = (WeatherDataForObservable) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("内置方法：" + temperature + "℃ and " + humidity + "%RH" + " and " + pressure + "Pa");
    }
}