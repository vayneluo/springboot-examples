package com.xiaoluo.boot.integrate.juc;

import lombok.Data;

/**
 * @classname: ImmutableSmsInfo
 * @description: 不可变类
 * @author: Vayne.Luo
 * @date 2021/6/11 15:54
 */
@Data
public final class ImmutableSmsInfo {

    private final long id;

    private final String url;

    private final Long maxSize;

    public ImmutableSmsInfo(long id, String url, Long maxSize) {
        this.id = id;
        this.url = url;
        this.maxSize = maxSize;
    }

    public ImmutableSmsInfo(ImmutableSmsInfo smsInfo) {
        this.id = smsInfo.getId();
        this.url = smsInfo.getUrl();
        this.maxSize = smsInfo.getMaxSize();
    }
}
