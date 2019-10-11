package com.xiaoluo.dingding.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DingTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingTaskApplication.class, args);
    }

}
