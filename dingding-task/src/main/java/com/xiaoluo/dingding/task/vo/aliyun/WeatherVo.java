package com.xiaoluo.dingding.task.vo.aliyun;

import com.xiaoluo.dingding.task.vo.BaseVo;
import com.xiaoluo.dingding.task.vo.RspData;
import lombok.Data;

/**
 * @classname: WeatherVo
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/10/30 17:10
 */
@Data
public class WeatherVo extends RspData {

    private CurrentWeatherVo now;

}
