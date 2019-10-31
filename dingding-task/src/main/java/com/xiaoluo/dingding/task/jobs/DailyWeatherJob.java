package com.xiaoluo.dingding.task.jobs;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.xiaoluo.dingding.task.common.constants.AppConfigConstants;
import com.xiaoluo.dingding.task.service.common.WeatherCacheService;
import com.xiaoluo.dingding.task.service.weather.WeatherService;
import com.xiaoluo.dingding.task.utils.RobotUtils;
import com.xiaoluo.dingding.task.vo.aliyun.CurrentWeatherVo;
import com.xiaoluo.dingding.task.vo.aliyun.WeatherVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @classname: DailyWeatherJob
 * @description: 每日天气预报
 * @author: Vayne.Luo
 * @date 2019/10/30 16:20
 */
@Component
@Slf4j
public class DailyWeatherJob {

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherCacheService weatherCacheService;

    @Scheduled(cron = "0 0 9 ? * MON-FRI")
    public void dailyWeather(){
        WeatherVo weatherVo = weatherService.getShangHaiTodayWeather();
        if(null != weatherVo){
            sendWeatherInfo(weatherVo);
        }
    }

    /**
     * @description: 发送今日天气
     * @author: Vayne.Luo
     * @date: 2019/10/31 14:45
     */
    private void sendWeatherInfo(WeatherVo weatherVo) {
        CurrentWeatherVo now = weatherVo.getNow();
        if(now == null){
            return;
        }
        DingTalkClient client = new DefaultDingTalkClient(RobotUtils.getFinalUrl(AppConfigConstants.WANG_WEB_HOOK,AppConfigConstants.WANG_SECRET));
        String url = weatherCacheService.getPicUrlByCode(now.getCity().getWeather_code());
        String imgUrl = AppConfigConstants.RES_SERVER + url;
        log.info("天气图片地址：{}",imgUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("今日天气");
        StringBuilder sb = new StringBuilder();
        sb.append("#### 【上海天气】 \n")
                .append("> ")
                .append(now.getCity().getWeather()).append("，")
                .append(now.getCity().getNight_air_temperature()).append("℃/").append(now.getCity().getDay_air_temperature())
                .append("℃，")
                .append(now.getCity().getWind_direction()).append(" ，").append(now.getCity().getWind_power())
                .append("\n\n")
                .append("> ![weather]("+ imgUrl +") \n")
                .append("> ###### 今天是 *"+now.getDetail().getDate()+ "* ，星期"+ now.getDetail().getWeek()+"，农历 "+ now.getDetail().getNongli() +" \n")
                .append("> ###### 早晨起床，拥抱太阳，让身体充满，满满的正能量");

        markdown.setText(sb.toString());
        request.setMarkdown(markdown);
        try {
            log.info("开始发送天气预报，当前时间 {}", RobotUtils.getDateStr());
            client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
