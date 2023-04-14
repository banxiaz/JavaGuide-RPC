package com.bai.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {
    // 代理类中的真实对象
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 动态生成的代理类
     * @param method 与代理对象调用的方法相对应
     * @param args 当前method方法的参数
     * @return 返回值
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //开始之前，添加操作
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        //调用方法之后，添加操作
        System.out.println("after method " + method.getName());
        return result;
    }
}
