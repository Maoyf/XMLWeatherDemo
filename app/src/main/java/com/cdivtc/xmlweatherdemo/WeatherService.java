package com.cdivtc.xmlweatherdemo;

import android.util.Xml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 28461 on 2018/10/15.
 */

public class WeatherService {
    //解析XML文件，返回天气信息的集合
    //第一步:创建一个公有的静态方法，用来解析XML信息
    //用list集合来返回信息
    public static List<WeatherInfo> getInfoFromXML(InputStream is) throws XmlPullParserException, IOException {
        //创建一个XML解析器  Pull解析
        XmlPullParser parser = Xml.newPullParser();
        //初始化解析器，第一个参数代表包含xml的数据，第二个参数是编码方式
        parser.setInput(is,"utf-8");
        //定义一个集合，用来存放解析后的数据，初始值为空
        List<WeatherInfo> weatherInfos = null;
        //一个城市的天气信息
        WeatherInfo weatherInfo = null;
        //得到当前事件的类型   解析器调用getEventType()方法得到当前是否是文件的结束，还是标签节点
        int type = parser.getEventType();
        //通过循环来进行判断是否是文件的结束标记
        while (type != XmlPullParser.END_DOCUMENT){
            //通过switch case 来判断是哪种类型，是文档的标记，还是节点标记
            switch (type){
                //一个节点的开始标签
                case XmlPullParser.START_TAG:
                    //解析到全局开始的标签infos根据节点
                    if ("infos".equals(parser.getName())){
                        weatherInfos = new ArrayList<WeatherInfo>();
                    } else if ("city".equals(parser.getName())) {
                        //实例化一个城市的天气信息
                        weatherInfo = new WeatherInfo();
                        //获取到这个城市对应的属性值
                        String idStr = parser.getAttributeValue(0);
                        //设置城市的id
                        weatherInfo.setId(idStr);
                    }else if ("temp".equals(parser.getName())){
                        //得到该节点的信息，并保存到temp变量中
                        String temp = parser.nextText();
                        //将temp作为这个城市的temp值存放
                        weatherInfo.setTemp(temp);
                    }else if ("weather".equals(parser.getName())){
                        //得到该节点的信息，并保存到weather变量中
                        String weather = parser.nextText();
                        //将temp作为这个城市的temp值存放
                        weatherInfo.setWeather(weather);
                    }
                    else if ("name".equals(parser.getName())){
                        //得到该节点的信息，并保存到name变量中
                        String name = parser.nextText();
                        //将temp作为这个城市的temp值存放
                        weatherInfo.setName(name);
                    }else if ("pm".equals(parser.getName())){
                        //得到该节点的信息，并保存到pm变量中
                        String pm = parser.nextText();
                        //将temp作为这个城市的temp值存放
                        weatherInfo.setPm(pm);
                    }else if ("wind".equals(parser.getName())){
                        //得到该节点的信息，并保存到wind变量中
                        String wind = parser.nextText();
                        //将temp作为这个城市的temp值存放
                        weatherInfo.setWind(wind);
                    }
                    break;
                    //一个节点结束的标签
                case XmlPullParser.END_TAG:
                    //表示一个城市的信息处理完毕，city的结束标签
                    if ("city".equals(parser.getName())){
                        //将一个城市的天气信息添加到天气信息的集合
                        weatherInfos.add(weatherInfo);
                        //清空之前的一个城市的天气信息
                        weatherInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return weatherInfos;
    }

    //解析JSON文件返回天气信息集合
    public static List<WeatherInfo> getInfosFromJson(InputStream is) throws IOException {
        //实例化一个字节类型的数组，用来存放获取的信息
        byte[] buffer = new byte[is.available()];
        //读取is中的内容
        is.read(buffer);
        //实例化一个字符串类型的变量用来存放读取的内容（信息）
        //JSON数据为纯文本，所以要转换为String类型
        String json = new String(buffer, "utf-8");
        //使用Gson库解析JSON数据
        //实例化一个Gson对象
        Gson gson = new Gson();
        //Type java反射的一个接口
        //通过实例化一个Gosn的TypeToken匿名内部类，来调用getType方法，获取到对应的接口类
        //泛型是天气信息实体类，得到天气信息集合
        Type listType = new TypeToken<List<WeatherInfo>>(){}.getType();
        //通过gosn调用fromJson方法得到天气信息的集合
        List<WeatherInfo> weatherInfos = gson.fromJson(json, listType);
        return weatherInfos;
    }
}
