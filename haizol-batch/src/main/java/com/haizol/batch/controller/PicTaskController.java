/*
package com.haizol.batch.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.haizol.batch.service.LastCommentTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

*/
/**
 * @classname: LastCommentTaskController
 * @description: 任务
 * @author: Vayne.Luo
 * @date 2020/9/8 09:41
 *//*

@Slf4j
@RestController
public class PicTaskController {
    @Autowired
    LastCommentTaskService taskService;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return restTemplateBuilder.build();
    }

    private static final String DOWNLOAD_URL = "https://res.haizol.com/download-static?ne=1&webp=1&thn=240180&code=";
    private static final String DIR_PREFIX = "D:\\pic\\";

    */
/** 动态流速 **//*

    private double rate = 1;

    @GetMapping("/pic/execute")
    public void executeTask(){
        AtomicInteger count = new AtomicInteger();
        log.info("------------------批处理任务启动------------------ 开始时间:{}", LocalDateTime.now());
        CsvData csvData = null;
        Reader readerTemp = null;

        try {
            ClassPathResource resource = new ClassPathResource("pic.csv");
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
        rows.forEach(task -> {
            List<String> rawList = task.getRawList();
            log.info("当前处理数据行：{}", rawList);
            //FileUtil.
            String compId = rawList.get(0);
            String hashCode = rawList.get(1);
            String downloadUrl = DOWNLOAD_URL + hashCode;
            String path = DIR_PREFIX + compId;
            boolean exist = FileUtil.exist(path);
            if(!exist){
                FileUtil.mkdir(path);
            }
            writePicture(path,downloadUrl);
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
            ThreadUtil.sleep(200);
        });
        log.info("总进度：100%");
        log.info("------------------批处理任务结束------------------ 结束时间:{}", LocalDateTime.now());

    }

    private void writePicture(String path, String downloadUrl) {
        // 写入文件
        ResponseEntity<byte[]> response = restTemplate.getForEntity(downloadUrl, byte[].class);
        log.info("文件下载请求结果状态码：" + response.getStatusCode());
        if(response.getStatusCode().equals(HttpStatus.OK))
        {
            String fileName = response.getHeaders().get("Content-Disposition").get(0);
            fileName = StrUtil.split(fileName, ";") [1];
            fileName = StrUtil.split(fileName,"=")[1].replace("\"", "");
            String finalPath = path + "\\" + fileName;
            log.info("文件名为: {}",fileName);
            FileUtil.writeBytes(response.getBody(),finalPath);
        }
    }
}
*/
