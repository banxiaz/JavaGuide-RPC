package com.bai.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DebugMethodInterceptor implements MethodInterceptor {
    /**
     * @param o           被代理的对象（需要增强的对象）
     * @param method      被拦截的方法（需要增强的方法
     * @param objects     方法参数
     * @param methodProxy 用于调用原始方法
     * @return 返回
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // do before
        System.out.println("before method " + method.getName());
        // do this
        Object object = methodProxy.invokeSuper(o, objects);
        // do after
        System.out.println("after method " + method.getName());
        return object;
    }
}
