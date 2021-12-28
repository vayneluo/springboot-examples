package com.xiaoluo.boot.integrate.base;

/**
 * @classname: FactoryBean
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/12/27 10:24
 */
public interface FactoryBean<T> {

    /** 接口的字段默认是static和final的 */
    String URL = "TEST URL";

    /** 接口方法默认修饰符为public,且不允许定义为private，protected */
    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
