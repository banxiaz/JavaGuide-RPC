package com.bai.testcallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            System.out.println("callable");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
            return 1234;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        System.out.println(1);

        new Thread(futureTask).start(); //这里才开始线程
        System.out.println(2);

        System.out.println(futureTask.get()); //阻塞直到超时或者返回结果
        System.out.println(3);

    }
}
