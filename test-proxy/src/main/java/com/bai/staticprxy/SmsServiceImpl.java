package com.bai.staticprxy;

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message" + message);
        return message;
    }
}
