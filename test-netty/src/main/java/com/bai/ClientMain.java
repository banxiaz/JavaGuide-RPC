package com.bai;

import com.bai.dto.RpcRequest;
import com.bai.dto.RpcResponse;
import com.bai.netty.server.NettyClient;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        RpcRequest rpcRequest = RpcRequest.builder().interfaceName("interface").methodName("hello").build();
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8888);
        for (int i = 0; i < 5; i++) {
            nettyClient.sendMessage(rpcRequest);
            Thread.sleep(1000 * 10);
        }
        RpcResponse rpcResponse = nettyClient.sendMessage(rpcRequest);
        System.out.println(rpcResponse.toString());
    }
}
