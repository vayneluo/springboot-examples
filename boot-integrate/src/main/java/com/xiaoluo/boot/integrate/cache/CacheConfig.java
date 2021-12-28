package com.xiaoluo.boot.integrate.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    /**
     * 配置缓存管理器caffeineCacheManager
     *
     * @return 缓存管理器
     */
    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {
        CaffeineCache hotSupplier = buildCache("hot-supplier",5);
        CaffeineCache hotUser = buildCache("hot-user",10);
        CaffeineCache dynamic = buildCache("dynamic",3);
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(hotSupplier,hotUser,dynamic));
        return cacheManager;
    }

    private CaffeineCache buildCache(String cacheName , int ttl){
        return new CaffeineCache(cacheName,Caffeine.newBuilder()
                .expireAfterAccess(ttl, TimeUnit.SECONDS)
                .initialCapacity(100)
                .maximumSize(1000)
                .build());
    }

    /**
     * 配置缓存管理器caffeineCacheManager
     *
     * @return 缓存管理器
     *//*
    @Bean("hotSupplierCacheManager")
    public CacheManager hotSupplierCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(30, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000));
        return cacheManager;
    }*/

}