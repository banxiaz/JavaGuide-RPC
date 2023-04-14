package com.bai;

import com.bai.myanno.CustomImport;

@CustomImport(beanName = "hellService1")
public class HelloService {
    public void show() {
        System.out.println("HelloService->show()");
    }
}
