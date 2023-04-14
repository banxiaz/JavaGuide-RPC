package com.bai;

public class ChinaNetCDN implements UploadCDN{
    @Override
    public void upload(String url) {
        System.out.println("upload to chinaNet CDN!");
    }
}
