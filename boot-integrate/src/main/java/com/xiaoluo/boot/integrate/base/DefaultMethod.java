package com.xiaoluo.boot.integrate.base;

/**
 * @classname: DefaultMethod
 * @description: 默认方法的接口
 * @author: Vayne.Luo
 * @date 2021/12/27 10:14
 */
public interface DefaultMethod {

    void sayHello();

    default void doNothing(){
        System.out.println("do nothing");
    }
}
