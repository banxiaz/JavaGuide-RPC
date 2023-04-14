package com.bai.jdk;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {
    /**
     *
     * @param target 被代理的对象
     * @return 代理对象
     */
    public static Object getProxy(Object target) {

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), //目标类的加载器，用于加载代理对象
                target.getClass().getInterfaces(), //代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target)); //代理对象的自定义InvocationHandle，需要实现InvocationHandle接口
    }

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
