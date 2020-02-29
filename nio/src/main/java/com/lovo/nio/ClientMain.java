package com.lovo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        //第一个客户端
        SocketChannel socketChannel=SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //去连接服务器
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
        //第二个客户端
        SocketChannel socketChannel1=SocketChannel.open();
        //设置为非阻塞
        socketChannel1.configureBlocking(false);
        //去连接服务器
        socketChannel1.connect(new InetSocketAddress("127.0.0.1",8888));


        ClientUtil clientUtil=new ClientUtil();
        //把两个客户端的通道放入到集合
        List<SocketChannel> list=new ArrayList<>();
        list.add(socketChannel);
        list.add(socketChannel1);

        clientUtil.bindChannel(list);
    }
}
