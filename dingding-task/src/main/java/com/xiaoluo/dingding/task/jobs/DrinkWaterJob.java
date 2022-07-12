package com.xiaoluo.dingding.task.jobs;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.xiaoluo.dingding.task.common.constants.AppConfigConstants;
import com.xiaoluo.dingding.task.common.constants.RedisKeyConstants;
import com.xiaoluo.dingding.task.service.weather.WaterCountService;
import com.xiaoluo.dingding.task.utils.RobotUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname: DrinkWaterJob
 * @description: 喝水
 * @author: Vayne.Luo
 * @date 2019/10/9 11:52
 */
//@Component
@Slf4j
public class DrinkWaterJob {
    /** @ 人员 **/
    private static final String VAYNE_MOBILE = "18621706355";
    private static final String LCHM_MOBILE = "17330797616";

    @Autowired
    WaterCountService waterCountService;

    @Scheduled(cron="0 0 9,11,13,15,17,19 ? * MON-FRI")
    public void needDrinkWater(){
        DingTalkClient client = new DefaultDingTalkClient(RobotUtils.getFinalUrl(AppConfigConstants.WANG_WEB_HOOK,AppConfigConstants.WANG_SECRET));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // Redis中获取最新杯数，如果过期，则重新开始计数
        final Long count = waterCountService.getWaterCount(RedisKeyConstants.WANG_WATER_COUNT_KEY);
        // 设置@的人
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList(VAYNE_MOBILE,LCHM_MOBILE));
        at.setIsAtAll("true");
        request.setAt(at);
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("吨吨提醒");
        StringBuilder builder = new StringBuilder();
        builder.append("#### 【吨吨时间到】 \n\n")
                .append("> 大口吨起来,吨吨吨 \n\n")
                .append("> 这是你今天喝的第 **"+ count +"** 杯水哦，太棒了你！\n\n")
                .append("> ###### 本消息来自Timi "+ RobotUtils.getDateStr() +"  发布 \n");
        markdown.setText(builder.toString());
        request.setMarkdown(markdown);
        try {
            log.info("开始发送消息" + RobotUtils.getDateStr());
            client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
