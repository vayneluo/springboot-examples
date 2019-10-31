package com.xiaoluo.dingding.task.utils;

import com.taobao.api.internal.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: RobotUtils
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/10/31 13:17
 */
public class RobotUtils {

    /**
     * @description: 拼接最终发送消息的URL
     * @author: Vayne.Luo
     * @date: 2019/10/30 11:57
     */
    public static String getFinalUrl(String webHook, String secret){
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = null;

        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String encode = URLEncoder.encode(Base64.encodeToString(signData, true), "UTF-8");
            String finalUrl = webHook + "&timestamp="+timestamp+"&sign="+encode;
            return finalUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * @description: 格式化当前时间
     * @author: Vayne.Luo
     * @date: 2019/10/30 11:58
     */
    public static String getDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
