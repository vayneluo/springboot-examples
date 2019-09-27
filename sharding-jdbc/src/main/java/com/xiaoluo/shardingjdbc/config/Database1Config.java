package com.xiaoluo.shardingjdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @classname: Database0Config
 * @description: 数据源 1
 * @author: Vayne.Luo
 * @date 2019/9/27 14:01
 */
@Data
@Component
@ConfigurationProperties(prefix = "database1")
public class Database1Config {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private String databaseName;

    public DataSource createDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(getDriverClassName());
        druidDataSource.setUrl(getUrl());
        druidDataSource.setUsername(getUsername());
        druidDataSource.setPassword(getPassword());
        return druidDataSource;
    }
}
