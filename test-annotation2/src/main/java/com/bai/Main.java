package com.bai;

import com.bai.anno.EnableCustomImport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableCustomImport(packages = {"com.bai"})
public class Main {
    public static void main(String[] args) {
        System.out.println(123);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        Hello hello = (Hello) applicationContext.getBean("hello");
        System.out.println(hello);
        hello.sayHello();
        System.out.println(hello.excelName);
    }
}
