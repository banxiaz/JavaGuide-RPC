package com.bai;

import com.bai.anno.CustomAnnotation;
import com.bai.anno.Excel;

@CustomAnnotation(baseName = "this is CustomAnnotation")
public class Hello {
    //已经实现了通过反射获取name的值和将excelName设置为反射获取的值！
    @Excel(name = "can you print excel")
    public String excelName;

    public void sayHello() {
        System.out.println("Hello syaHello!");
    }
}
