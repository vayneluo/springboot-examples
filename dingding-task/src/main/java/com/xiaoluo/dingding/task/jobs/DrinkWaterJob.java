package com.xiaoluo.dingding.task.jobs;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.xiaoluo.dingding.task.common.constants.AppConfigConstants;
import com.xiaoluo.dingding.task.utils.RobotUtils;
import lombok.extern.slf4j.Slf4j;
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
@Component
@Slf4j
public class DrinkWaterJob {
    /** @ 人员 **/
    private static final String VAYNE_MOBILE = "18621706355";
    private static final String LCHM_MOBILE = "17330797616";

    /** 喝水计数器 **/
    private AtomicInteger waterCount = new AtomicInteger(0);

    /** 每天喝水杯数 **/
    private static final int WATER_TOTAL = 8;

    /** 每天9点开始重置喝水杯数 **/
    private static final int HOUR_OF_DAY = 9;

    @Scheduled(cron="0 0 9,10,11,13,14,15,16,17 ? * MON-FRI")
    public void needDrinkWater(){
        DingTalkClient client = new DefaultDingTalkClient(RobotUtils.getFinalUrl(AppConfigConstants.WANG_WEB_HOOK,AppConfigConstants.WANG_SECRET));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // 喝水次数
        final int currentValue = waterCount.get();
        if(currentValue == WATER_TOTAL || needReset()){
            // 重置喝水次数
            waterCount.set(0);
        }
        final int count = waterCount.incrementAndGet();
        // 设置@的人
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList(VAYNE_MOBILE,LCHM_MOBILE));
        at.setIsAtAll("true");
        request.setAt(at);
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("旺仔友情提醒");
        StringBuilder builder = new StringBuilder();
        builder.append("#### 【旺仔友情提醒】 \n\n")
                .append("> 又到了大家最爱的喝水环节了 \n\n")
                .append("> 这是你今天喝的第 **"+count+"** 杯水哦，加油加油！\n\n")
                .append("> ###### 本消息来自旺仔Iphone 11 Pro "+ RobotUtils.getDateStr() +"  发布 \n");
        markdown.setText(builder.toString());
        request.setMarkdown(markdown);
        log.info("消息体：{}",request);
        try {
            log.info("开始发送消息");
            client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 判断时间是否重置喝水杯数
     */
    private boolean needReset() {
        Calendar cal=Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour == HOUR_OF_DAY;
    }
}
