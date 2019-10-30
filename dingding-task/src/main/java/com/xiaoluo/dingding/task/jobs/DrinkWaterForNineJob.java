package com.xiaoluo.dingding.task.jobs;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
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
public class DrinkWaterForNineJob {

    /** 钉钉WEB_HOOK 地址**/
    private static final String WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=de107d318a019aa2860a5e23c37fe4584a45e4920db505f7258b30d911b26559";

    /** 签名 **/
    private static final String SECRET = "SECf88898a525adea7a3d0cbe616d805d4cfc0bf54dbb27fab1ef03495fadcc7f2a";

    /** 喝水计数器 **/
    private AtomicInteger waterNineCount = new AtomicInteger(0);

    /** 每天喝水杯数 **/
    private static final int WATER_TOTAL = 6;

    /** 每天9点开始重置喝水杯数 **/
    private static final int HOUR_OF_DAY = 10;

    @Scheduled(cron="0 0 10,11,14,15,16,17 ? * MON-FRI")
    public void needDrinkWater() throws Exception{
        DingTalkClient client = new DefaultDingTalkClient(getFinalUrl());
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // 喝水次数
        final int currentValue = waterNineCount.get();
        if(currentValue == WATER_TOTAL || needReset()){
            // 重置喝水次数
            waterNineCount.set(0);
        }
        final int count = waterNineCount.incrementAndGet();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("九九友情提醒");
        StringBuilder builder = new StringBuilder();
        builder.append("#### 【九九友情提醒】 \n\n")
                .append("> 又到了大家最爱的喝水环节了 \n\n")
                .append("> 这是你今天喝的第 **"+count+"** 杯水哦，加油加油！\n\n")
                .append("> ###### 本消息来自小九家的木木&咕噜 "+getDateStr() +"  发布 \n");
        markdown.setText(builder.toString());
        request.setMarkdown(markdown);
        try {
            log.info("开始发送消息" + getDateStr());
            client.execute(request);
            log.info("喝水提醒完毕");
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
