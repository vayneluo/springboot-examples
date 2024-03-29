package com.xiaoluo.boot.integrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class BootIntegrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootIntegrateApplication.class, args);
    }

}
