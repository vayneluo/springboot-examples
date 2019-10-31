package com.xiaoluo.dingding.task.vo.aliyun;

import lombok.Data;

/**
 * @classname: WeatherVo
 * @description: 城市
 * @author: Vayne.Luo
 * @date 2019/10/30 17:10
 */
@Data
public class CityVo {
    private Integer night_air_temperature;
    private Integer day_air_temperature;
    private String wind_direction;
    private String wind_power;
    private String weather;
    private String weather_code;
}
