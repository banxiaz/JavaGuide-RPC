package com.bai.staticprxy;

public class SmsProxy implements SmsService {
    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        //调用方法前，我们可以添加自己的操作
        System.out.println("before method send()");
        smsService.send(message);
        //调用方法后，我们同样可以添加自己的操作
        System.out.println("after method send()");
        return null;
    }
}
