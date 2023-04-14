package com.bai.testcallable;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//Executor是线程池的顶层接口
public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        /*
        corePoolSize：核心线程池的大小
        maximumPoolSize：最大线程池的大小
        keepAliveTime：当线程池中线程数大于corePoolSize，并且没有可执行任务时大于corePoolSize那部分线程的存活时间
        unit：keepAliveTime的时间单位
        workQueue：用来暂时保存任务的工作队列
        threadFactory：线程工厂提供线程的创建方式，默认使用Executors.defaultThreadFactory()
        handler：当线程池所处理的任务数超过其承载容量或关闭后继续有任务提交时，所调用的拒绝策略
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                10,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                // new ThreadPoolExecutor.DiscardOldestPolicy());
                Executors.defaultThreadFactory());
        System.out.println("1");
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> System.out.println(finalI + " 1 " + Thread.currentThread().getName()));
            int finalI1 = i;
            threadPoolExecutor.execute(() -> System.out.println(finalI1 + " 2 " + Thread.currentThread().getName()));
        }
        System.out.println("2");
        threadPoolExecutor.shutdown();
    }
}
