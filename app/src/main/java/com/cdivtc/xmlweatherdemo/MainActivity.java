package com.cdivtc.xmlweatherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tvCity;
    private TextView tvWeather;
    private TextView tvTemp;
    private TextView tvWind;
    private TextView tvPm;
    private ImageView ivIcon;
    private Button btnBJ;
    private Button btnSH;
    private Button btnGZ;

    //存放信息的变量
    private Map<String,String> map;
    private List<Map<String,String>> list;
    private String temp,weather,name,pm,wind;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化变量，变量与控件绑定
        findViews();
        try {
        //读取weather1.xml中的文件
        //InputStream is = this.getResources().openRawResource(R.raw.weather1);
        //读取weather2.json文件中的信息
            InputStream is = this.getResources().openRawResource(R.raw.weather2);
        //把每个城市的天气信息集合存放到weatherInfos中
            //List<WeatherInfo> weatherInfos = WeatherService.getInfoFromXML(is);
            //调用工具类中的getInfosFromJson
            List<WeatherInfo> weatherInfos = WeatherService.getInfosFromJson(is);
            String infos = weatherInfos.toString();
            Log.d("天气信息", infos);
            //循环读取weatherInfos中每个城市的每一条天气信息
            list = new ArrayList<Map<String, String>>();
            //用foreach来遍历weatherInfos中的信息
            for (WeatherInfo info:weatherInfos) {
                //将每一条信息添加到map中，然后再添加到list集合中
                map = new HashMap<String, String>();
                map.put("temp",info.getTemp());
                map.put("weather",info.getWeather());
                map.put("name",info.getName());
                map.put("pm",info.getPm());
                map.put("wind",info.getWind());
                list.add(map);
            }
        } /*catch (XmlPullParserException e) {
            e.printStackTrace();
            Toast.makeText(this,"解析失败",Toast.LENGTH_SHORT).show();
        } */catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"解析失败",Toast.LENGTH_SHORT).show();
        }
        Button.OnClickListener bListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_sh:
                        getMap(0,R.drawable.cloud_sun);
                        break;
                    case R.id.btn_bj:
                        getMap(1,R.drawable.sun);
                        break;
                    case R.id.btn_gz:
                        getMap(2,R.drawable.clouds);
                        break;
                }
            }
        };
        btnSH.setOnClickListener(bListener);
        btnBJ.setOnClickListener(bListener);
        btnGZ.setOnClickListener(bListener);

    }

    private void getMap(int i, int cloud_sun) {
        //定义一个城市的天气变量 map类型
        Map<String,String> cityMap = list.get(i);
        //获取到每一条天气信息
        temp = cityMap.get("temp");
        weather = cityMap.get("weather");
        name = cityMap.get("name");
        pm = cityMap.get("pm");
        wind = cityMap.get("wind");
        //将每一条天气信息添加到对应的控件中
        tvTemp.setText(""+temp);
        tvCity.setText(name);
        tvWeather.setText(weather);
        tvWind.setText("风力  ： "+wind);
        tvPm.setText("pm："+pm);
        ivIcon.setImageResource(cloud_sun);
    }

    private void findViews() {
        tvCity = findViewById(R.id.tv_city);
        tvWeather = findViewById(R.id.tv_weather);
        tvPm = findViewById(R.id.tv_pm);
        tvTemp = findViewById(R.id.tv_temp);
        tvWind = findViewById(R.id.tv_wind);
        ivIcon = findViewById(R.id.iv_icon);
        btnBJ = findViewById(R.id.btn_bj);
        btnGZ = findViewById(R.id.btn_gz);
        btnSH = findViewById(R.id.btn_sh);
    }
}
