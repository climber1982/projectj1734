package com.lovo.nio;

import sun.security.provider.NativePRNG;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class Service {

    public static void main(String[] args) throws Exception {
        //创建服务器通道
        ServerSocketChannel channel=ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(8888));
       //创建选择器
       Selector selector= Selector.open();
       //选择器绑定通道
       //选择器注册到选择器上
        channel.register(selector, SelectionKey.OP_ACCEPT);
        //获得选择器注册的所有通道
        while (true) { //循环处理数据
            int num = selector.select();//获得已经就绪的通道
            if (num > 0) {
                //获取所有通道对应的key
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                  SelectionKey key=  iterator.next();
                  //如果为阻塞
                  if(key.isAcceptable()){
                      ServerSocketChannel channel1= (ServerSocketChannel) key.channel();
                        SocketChannel channel2=channel.accept();
                     if(null!=channel1) {
                         //把该通道设置为非阻塞
                         channel2.configureBlocking(false);
                         channel2.register(key.selector(), SelectionKey.OP_READ);
                     }
                  }else if(key.isReadable()){
                             //获取通道
                      SocketChannel socketChannel= (SocketChannel) key.channel();
                      //key以设置为读模式
                      key.interestOps(SelectionKey.OP_READ);
                      ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                      socketChannel.read(byteBuffer);
                      System.out.println(new String(byteBuffer.array()));
                      //key设置为写模式
                      key.interestOps(SelectionKey.OP_WRITE);
                      socketChannel.write(Charset.forName("utf-8").encode("hello 服务器"));
                       //socketChannel.close();//关闭通道
                  }

                        iterator.remove();
                }
            }
        }


    }
}
