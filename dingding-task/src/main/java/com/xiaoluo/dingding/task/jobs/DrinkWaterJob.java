package com.xiaoluo.dingding.task.jobs;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.Base64;
import lombok.extern.slf4j.Slf4j;
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
public class DrinkWaterJob {

    /** 钉钉WEB_HOOK 地址**/
    private static final String WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=92d7118cecfa9b004f342afddd92e27e7a81dde1ca91388abab481fbbbbab559";

    /** 签名 **/
    private static final String SECRET = "SEC27131130bf6e8ef21386271853368d532d58071625f7d710cf77979027990b9f";

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
    public void needDrinkWater() throws Exception{
        DingTalkClient client = new DefaultDingTalkClient(getFinalUrl());
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
                .append("> ###### 本消息来自旺仔Iphone 11 Pro "+getDateStr() +"  发布 \n");
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
     * @description: 拼接最终发送消息的URL
     * @author: Vayne.Luo
     * @date: 2019/10/30 11:57
     */ 
    private String getFinalUrl() throws Exception{
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + SECRET;
        Mac mac = null;
        mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(SECRET.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String encode = URLEncoder.encode(Base64.encodeToString(signData, true), "UTF-8");
        String finalUrl = WEB_HOOK + "&timestamp="+timestamp+"&sign="+encode;
        return finalUrl;
    }

    /**
     * @description: 格式化当前时间
     * @author: Vayne.Luo
     * @date: 2019/10/30 11:58
     */
    private String getDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
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
