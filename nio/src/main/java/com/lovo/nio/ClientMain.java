package com.lovo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));

        SocketChannel socketChannel1=SocketChannel.open();
        socketChannel1.configureBlocking(false);
        socketChannel1.connect(new InetSocketAddress("127.0.0.1",8888));
        ClientUtil clientUtil=new ClientUtil();
        List<SocketChannel> list=new ArrayList<>();
        list.add(socketChannel);
        list.add(socketChannel1);
        clientUtil.bindChannel(list);
    }
}
