package com.bai.NIO.reactor;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(8888);
        new Thread(reactor).start();
        System.out.println("main finish");
    }
}
