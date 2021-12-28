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

/**
 * @classname: DinnerJob
 * @description: 点外卖提醒
 * @author: Vayne.Luo
 * @date 2021/10/21 13:44
 */
@Component
@Slf4j
public class WorkSummaryJob {

    @Scheduled(cron="0 30 17 ? * FRI")
    public void needDinner(){
        DingTalkClient client = new DefaultDingTalkClient(RobotUtils.getFinalUrl(AppConfigConstants.FRONT_END_TEAM_URL
                , AppConfigConstants.FRONT_END_TEAM_URL_SECRET));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // 设置@的人
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll("true");
        request.setAt(at);
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("周报提醒");
        StringBuilder builder = new StringBuilder();
        builder.append("#### 【周报提醒】 \n\n")
                .append("> 前端小伙伴，快写周报！！！\n\n")
                .append("> 快快快！！！\n\n")
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
