package com.xiaoluo.boot.integrate.proxy;

import org.junit.Test;

/**
 * @classname: ProxyTest
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/2/2 13:34
 */
public class ProxyTest {

    @Test
    public void testJDKProxy(){
        TargetInterface target = new Target();
        TargetInterface instance = (TargetInterface) new TargetProxy(target).getProxyInstance();
        instance.execute();
    }
}
