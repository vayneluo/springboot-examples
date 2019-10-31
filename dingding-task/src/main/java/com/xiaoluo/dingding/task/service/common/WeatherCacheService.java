package com.xiaoluo.dingding.task.service.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @classname: WeatherCacheService
 * @description: 天气缓存服务
 * @author: Vayne.Luo
 * @date 2019/10/31 10:23
 */
@Component
public class WeatherCacheService {

    private LoadingCache<String,Map<String,String>> loadingCache;

    private static final Map<String,String> picUrlMap = new HashMap<>();
    static {
        for (int i = 0; i < 100 ; i++) {
            if(i<10){
                picUrlMap.put("0" + i, "0" + i + ".png");
            }else {
                picUrlMap.put(String.valueOf(i),i + ".png");
            }
        }
        picUrlMap.put("301","301.png");
        picUrlMap.put("302","302.png");
    }

    @PostConstruct
    public void initCache(){
        loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(BigDecimal.ONE.longValue(), TimeUnit.MINUTES)
                .build(new CacheLoader<String, Map<String,String>>() {
                    @Override
                    public Map<String,String> load(String key) throws Exception {
                        return picUrlMap;
                    }
                });
    }

    /**
     * @description: 获取指定Code图片URL
     * @param: [code] weather_code
     * @author: Vayne.Luo
     * @date: 2019/10/31 11:51
     */
    public String getPicUrlByCode(String code){
        return getPicUrlMap().get(code);
    }

    /**
     * @description: 获取所有URL
     * @author: Vayne.Luo
     * @date: 2019/10/31 12:00
     */
    private Map<String,String> getPicUrlMap(){
        try {
            return loadingCache.get("picUrlMap");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
