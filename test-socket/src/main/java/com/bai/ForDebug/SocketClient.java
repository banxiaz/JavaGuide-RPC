package com.bai.ForDebug;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8888));

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write("123456789".getBytes());
            outputStream.write("abcdefg".getBytes());
            outputStream.write("hijklm".getBytes());
            outputStream.flush();
            byte[] bytes = new byte[128];
            int len = inputStream.read(bytes);
            System.out.println(len);
            System.out.println(new String(bytes, 0, len));

            socket.shutdownOutput(); //关闭输出方向，还可以接收数据，应该使用这种方式优雅关闭TCP连接
            // socket.close();
            // 读写全部关闭，服务端还有发送的数据到达时，客户端内核会回 RST 报文给服务端，然后内核会释放连接，这时就不会经历完整的 TCP 四次挥手

            bytes = new byte[128];
            len = inputStream.read(bytes);
            System.out.println(len);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
