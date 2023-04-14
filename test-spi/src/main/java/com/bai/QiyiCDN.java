package com.bai;

public class QiyiCDN implements UploadCDN{
    @Override
    public void upload(String url) {
        System.out.println("upload to qiyi CDN!");
    }
}
