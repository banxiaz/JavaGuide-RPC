package com.bai.NIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class NIOClient {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Socket socket;
                OutputStream out = null;
                try {
                    socket = new Socket("127.0.0.1", 8888);
                    out = socket.getOutputStream();
                    String s = "hello world";
                    out.write(s.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }
    }
}
