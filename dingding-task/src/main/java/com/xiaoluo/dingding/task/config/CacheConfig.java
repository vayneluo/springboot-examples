package com.xiaoluo.dingding.task.config;

import com.xiaoluo.dingding.task.service.common.RedisService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @classname: CacheConfig
 * @description: 缓存配置
 * @author: Vayne.Luo
 * @date 2019/10/31 09:45
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisService redisService(StringRedisTemplate stringRedisTemplate){
        RedisService redisService = new RedisService();
        redisService.setRedisTemplate(stringRedisTemplate);
        return redisService;
    }

}
