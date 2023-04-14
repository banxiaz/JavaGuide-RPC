package com.bai;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<UploadCDN> uploadCDNS = ServiceLoader.load(UploadCDN.class);
        for (UploadCDN u : uploadCDNS) {
            u.upload("filepath");
        }
    }
}
