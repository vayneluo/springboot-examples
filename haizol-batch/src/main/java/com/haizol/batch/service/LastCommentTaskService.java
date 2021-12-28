package com.haizol.batch.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.csv.CsvRow;
import com.google.common.util.concurrent.RateLimiter;
import com.haizol.batch.bean.LastCommentRsp;
import com.haizol.batch.bean.SearchReq;
import com.haizol.batch.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @classname: LastCommentTaskService
 * @description: 批处理任务
 * @author: Vayne.Luo
 * @date 2020/9/7 14:47
 */
@Service
@Slf4j
public class LastCommentTaskService {

    @Autowired
    UserMapper userMapper;

    /** 流速控制 **/
    private RateLimiter rateLimiter = RateLimiter.create(1);

    public List<LastCommentRsp> getLimitLastComment(Long maxCompId) {
        return userMapper.getLimitLastComment(maxCompId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateLastComment(List<String> rowList, double rate) {
        getLimiterRate(rate);
        rateLimiter.acquire();
        String compIdStr = rowList.get(0);
        String lastCommentStr = rowList.get(1);
        long time = Long.valueOf(lastCommentStr) * 1000;
        Long compId = Long.valueOf(compIdStr);
        Date lastComment = new Date(time);
        // 更新lastComment
        userMapper.updateUserLastComment(compId,lastComment);
    }

    private void getLimiterRate(double rate){
        if(rate != rateLimiter.getRate()){
            rateLimiter = RateLimiter.create(rate);
            log.info("动态修改流速:{}--{}",rateLimiter.getRate(),rate);
        }
    }
}
