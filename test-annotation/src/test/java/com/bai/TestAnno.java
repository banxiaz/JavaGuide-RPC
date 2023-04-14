package com.bai;

import com.bai.myanno.EnableCustomImport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestAnno {


    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        HelloService helloservice = (HelloService) context.getBean("hellService1");
        helloservice.show();
    }
}
