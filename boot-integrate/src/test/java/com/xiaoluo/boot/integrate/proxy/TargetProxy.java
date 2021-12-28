package com.xiaoluo.boot.integrate.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @classname: TargetProxy
 * @description: JDK 动态代理实现
 * @author: Vayne.Luo
 * @date 2021/2/2 13:24
 */
public class TargetProxy {

    private Object target;

    public TargetProxy(){

    }

    public TargetProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before execute");
                Object result = method.invoke(target, args);
                System.out.println("after execute");
                return result;
            }
        });
    }


}
