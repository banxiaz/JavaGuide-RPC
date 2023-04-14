package com.bai;

import org.springframework.stereotype.Component;

@Component("helloService3")
public class HelloService3 {
    public void show() {
        System.out.println("HelloService3->show()");
    }
}
