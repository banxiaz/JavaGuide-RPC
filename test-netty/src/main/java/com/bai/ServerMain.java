package com.bai;

import com.bai.netty.server.NettyServer;

public class ServerMain {
    public static void main(String[] args) {
        new NettyServer(8888).run();
    }
}
