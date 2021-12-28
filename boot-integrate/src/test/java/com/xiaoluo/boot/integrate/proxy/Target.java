package com.xiaoluo.boot.integrate.proxy;

/**
 * @classname: Target
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/2/2 13:22
 */
public class Target implements TargetInterface{
    @Override
    public void execute() {
        System.out.println("target object execute method");
    }
}
