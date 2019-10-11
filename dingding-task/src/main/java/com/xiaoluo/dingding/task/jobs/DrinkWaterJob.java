package com.xiaoluo.dingding.task.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @classname: DrinkWaterJob
 * @description: 喝水
 * @author: Vayne.Luo
 * @date 2019/10/9 11:52
 */
@Component
public class DrinkWaterJob {

    @Scheduled(cron="0 0 9,10,11,13,14,15,16,17 * * ? *")
    public void needDrinkWater(){
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add(1);
    }
}
