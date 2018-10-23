package com.cdivtc.xmlweatherdemo;

/**
 * Created by 28461 on 2018/10/15.
 */

public class WeatherInfo {
    //定义属性6个属性
    private String id;
    private String temp;
    private String weather;
    private String name;
    private String pm;
    private String wind;

    //创建getter和setter方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "id='" + id + '\'' +
                ", temp='" + temp + '\'' +
                ", weather='" + weather + '\'' +
                ", name='" + name + '\'' +
                ", pm='" + pm + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }
}
