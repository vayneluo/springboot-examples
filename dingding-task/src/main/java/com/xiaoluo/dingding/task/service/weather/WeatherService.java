package com.xiaoluo.dingding.task.service.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoluo.dingding.task.common.constants.AppConfigConstants;
import com.xiaoluo.dingding.task.utils.HttpUtils;
import com.xiaoluo.dingding.task.vo.aliyun.WeatherVo;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @classname: WeatherPicService
 * @description: 天气服务
 * @author: Vayne.Luo
 * @date 2019/10/31 10:48
 */
@Service
public class WeatherService {

    public WeatherVo getShangHaiTodayWeather() {
        String host = "https://iweather.market.alicloudapi.com";
        String path = "/address";
        String method = "GET";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + AppConfigConstants.WEATHER_APP_CODE);
        Map<String, String> querys = new HashMap<>();
        querys.put("area", "");
        querys.put("city", "上海");
        querys.put("needday", "1");
        querys.put("prov", "上海");
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String weatherJson = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(weatherJson);
            String data = jsonObject.getString("data");
            WeatherVo weatherVo = JSON.parseObject(data,WeatherVo.class);
            return weatherVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WeatherVo getBeiJingTodayWeather() {
        String host = "https://iweather.market.alicloudapi.com";
        String path = "/address";
        String method = "GET";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + AppConfigConstants.WEATHER_APP_CODE);
        Map<String, String> querys = new HashMap<>();
        querys.put("area", "东城");
        querys.put("city", "北京");
        querys.put("needday", "1");
        querys.put("prov", "北京");
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String weatherJson = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(weatherJson);
            String data = jsonObject.getString("data");
            WeatherVo weatherVo = JSON.parseObject(data,WeatherVo.class);
            return weatherVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
