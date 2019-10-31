package com.xiaoluo.dingding.task.vo.aliyun;

import lombok.Data;

/**
 * @classname: CurrentWeatherVo
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/10/30 17:21
 */
@Data
public class CurrentWeatherVo {

    private String id;

    private String area_name;

    private CityVo city;

    private WeatherDetailVo detail;
}
