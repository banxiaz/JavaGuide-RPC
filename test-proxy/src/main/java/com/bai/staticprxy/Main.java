package com.bai.staticprxy;

public class Main {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsService smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
