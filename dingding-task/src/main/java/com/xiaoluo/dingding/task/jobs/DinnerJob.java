package com.xiaoluo.dingding.task.jobs;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.xiaoluo.dingding.task.common.constants.AppConfigConstants;
import com.xiaoluo.dingding.task.common.constants.RedisKeyConstants;
import com.xiaoluo.dingding.task.utils.RobotUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @classname: DinnerJob
 * @description: 点外卖提醒
 * @author: Vayne.Luo
 * @date 2021/10/21 13:44
 */
//@Component
@Slf4j
public class DinnerJob {

    /** @ 人员 **/
    private static final String VAYNE_MOBILE = "18621706355";
    private static final String LCHM_MOBILE = "17330797616";

    //@Scheduled(cron="0 50 10 ? * MON-FRI")
    public void needDinner(){
        DingTalkClient client = new DefaultDingTalkClient(RobotUtils.getFinalUrl(AppConfigConstants.WANG_WEB_HOOK,AppConfigConstants.WANG_SECRET));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // 设置@的人
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList(VAYNE_MOBILE,LCHM_MOBILE));
        at.setIsAtAll("true");
        request.setAt(at);
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("干饭啦");
        StringBuilder builder = new StringBuilder();
        builder.append("#### 【干饭提醒】 \n\n")
                .append("> 艳华，都几点了，还不点外卖！！！\n\n")
                .append("> 皮特，都几点了，还不点外卖！！！\n\n")
                .append("> Criss，都几点了，还不点外卖！！！\n\n")
                .append("> ###### 本消息来自不知道是啥玩意的机器人 "+ RobotUtils.getDateStr() +"  发布 \n");
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
