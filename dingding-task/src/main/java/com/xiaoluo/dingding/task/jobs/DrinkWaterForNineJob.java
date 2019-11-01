package com.xiaoluo.dingding.task.jobs;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.Base64;
import com.xiaoluo.dingding.task.common.constants.AppConfigConstants;
import com.xiaoluo.dingding.task.common.constants.RedisKeyConstants;
import com.xiaoluo.dingding.task.service.weather.WaterCountService;
import com.xiaoluo.dingding.task.utils.RobotUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname: DrinkWaterJob
 * @description: 喝水
 * @author: Vayne.Luo
 * @date 2019/10/9 11:52
 */
@Component
@Slf4j
public class DrinkWaterForNineJob {

    @Autowired
    WaterCountService waterCountService;

    @Scheduled(cron="0 0 10,11,14,15,16,17 ? * MON-FRI")
    public void needDrinkWater(){
        DingTalkClient client = new DefaultDingTalkClient(RobotUtils.getFinalUrl(AppConfigConstants.NINE_WEB_HOOK,AppConfigConstants.NINE_SECRET));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // Redis中获取最新杯数，如果过期，则重新开始计数
        final Long count = waterCountService.getWaterCount(RedisKeyConstants.NINE_WATER_COUNT_KEY);
        // 设置@的人
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll("true");
        request.setAt(at);
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("九九友情提醒");
        StringBuilder builder = new StringBuilder();
        builder.append("#### 【九九友情提醒】 \n\n")
                .append("> 又到了大家最爱的喝水环节了 \n\n")
                .append("> 这是你今天喝的第 **"+count+"** 杯水哦，加油加油！\n\n")
                .append("> ###### 本消息来自小九家的木木&咕噜 "+ RobotUtils.getDateStr() +"  发布 \n");
        markdown.setText(builder.toString());
        request.setMarkdown(markdown);
        try {
            log.info("开始发送消息" + RobotUtils.getDateStr());
            client.execute(request);
            log.info("喝水提醒完毕");
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
