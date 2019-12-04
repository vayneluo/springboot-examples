package com.xiaoluo.boot.integrate.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @classname: GuavaCacheService
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/10/11 18:37
 */
@Component
@Slf4j
public class GuavaCacheService {

    private LoadingCache<String,List<String>> localCache;

    @PostConstruct
    private void init(){
        localCache  = CacheBuilder.newBuilder()
                .expireAfterWrite(3L, TimeUnit.SECONDS)
                .build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String key) throws Exception {
                        System.out.println("当前load时间 " + System.currentTimeMillis());
                        return Arrays.asList(String.valueOf(System.currentTimeMillis()));
                    }
                });
    }

    public List<String> getListCache() {
        try {
            return localCache.get("Vayne");
        } catch (ExecutionException e) {
            e.printStackTrace();
            //log.error("get activity error", e);
        }
        return new ArrayList<>();
    }

}
