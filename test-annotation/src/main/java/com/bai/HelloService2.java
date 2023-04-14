package com.bai;

import com.bai.myanno.CustomImport;

@CustomImport(beanName = "helloService2")
public class HelloService2 {
    public void show() {
        System.out.println("HelloService2->show()");
    }
}
