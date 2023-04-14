package com.bai;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DebugClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8888));

            String msg = "0123456789";
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write(msg.getBytes());
            outputStream.write(msg.getBytes());
            outputStream.write(msg.getBytes());
            outputStream.flush();
            // socket.shutdownOutput(); //关闭输出方向，还可以接收数据
            socket.close();
            // 读写全部关闭，服务端还有发送的数据到达时，客户端内核会回 RST 报文给服务端，然后内核会释放连接，这时就不会经历完整的 TCP 四次挥手

            int len = inputStream.read(new byte[128]);
            System.out.println(len);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
