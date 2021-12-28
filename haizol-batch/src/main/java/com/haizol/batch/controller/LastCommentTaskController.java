package com.haizol.batch.controller;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.NumberUtil;
import com.haizol.batch.service.LastCommentTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname: LastCommentTaskController
 * @description: 任务
 * @author: Vayne.Luo
 * @date 2020/9/8 09:41
 */
@Slf4j
//@RestController
public class LastCommentTaskController {
    @Autowired
    LastCommentTaskService taskService;

    /** 动态流速 **/
    private double rate = 1;

    @GetMapping("/modify/{rate}")
    public String modifyRate(@PathVariable(value = "rate") double rate){
        log.info("修改流速值为:{}",rate);
        this.rate = rate;
        return "success";
    }

    @GetMapping("/execute")
    public void executeTask(){
        AtomicInteger count = new AtomicInteger();
        log.info("------------------批处理任务启动------------------ 开始时间:{}", LocalDateTime.now());
        CsvData csvData = null;
        Reader readerTemp = null;

        try {
            ClassPathResource resource = new ClassPathResource("prod_task.csv");
            InputStream inputStream = resource.getInputStream();
            readerTemp = new InputStreamReader(inputStream);
            CsvReader reader = CsvUtil.getReader();
            csvData = reader.read(readerTemp);
        } catch (IOException e) {
            log.error("文件读取异常");
            e.printStackTrace();
        }finally {
            if(readerTemp != null){
                try {
                    readerTemp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(null == csvData){
            log.error("csv 读取数据失败");
            return;
        }
        List<CsvRow> rows = csvData.getRows();
        log.info("读取CSV数据源，待更新总条数为 {}",rows.size());
        // 2、更新上次notes时间 限流处理 每秒处理一条
        rows.forEach(task -> {
            log.info("当前处理数据行：{}",task.getRawList());
            try {
                taskService.updateLastComment(task.getRawList(),rate);
            }catch (Exception e){
                log.info("异常数据：{}",task.getRawList());
                log.error("更新上次Notes时间异常,{}",e.getMessage());
            }
            count.getAndIncrement();
            int currentCount = count.intValue();
            if(currentCount % 30 == 0){
                double progress = 0;
                try {
                    progress = NumberUtil.div(currentCount, rows.size()) * 100;
                }catch (Exception e){
                    log.error("计算错误");
                }
                log.info("总进度：{}%,目前已处理了{}条",progress, currentCount);
            }
        });
        log.info("总进度：100%");
        log.info("------------------批处理任务结束------------------ 结束时间:{}", LocalDateTime.now());

    }
}
