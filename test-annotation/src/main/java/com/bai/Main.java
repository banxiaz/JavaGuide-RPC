package com.bai;

import com.bai.myanno.EnableCustomImport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableCustomImport(packages = "com.bai")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        HelloService helloservice = (HelloService) context.getBean("hellService1");
        helloservice.show();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
