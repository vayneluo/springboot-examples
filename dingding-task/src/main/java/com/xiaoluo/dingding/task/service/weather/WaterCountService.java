package com.xiaoluo.dingding.task.service.weather;

import com.xiaoluo.dingding.task.common.constants.AppConfigConstants;
import com.xiaoluo.dingding.task.service.common.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @classname: WaterCountService
 * @description: 计数器
 * @author: Vayne.Luo
 * @date 2019/11/1 10:24
 */
@Service
@Slf4j
public class WaterCountService {

    @Autowired
    RedisService redisService;

    /**
     * @description: 获取当前喝水杯数
     * @param: [key] JOB key
     * @author: Vayne.Luo
     * @date: 2019/11/1 13:47
     */
    public Long getWaterCount(String key){
        String current = redisService.get(key);
        if(StringUtils.isBlank(current)){
            log.info("{},初始化杯数为0-------",key);
            redisService.setEx(key,"0", AppConfigConstants.COUNT_EXPIRE, TimeUnit.HOURS);
        }
        return redisService.incrBy(key,AppConfigConstants.ADD_DELTA);
    }
}
