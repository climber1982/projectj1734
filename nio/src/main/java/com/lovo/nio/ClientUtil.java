package com.lovo.nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

public class ClientUtil {

    public  void bindChannel(List<SocketChannel> channels) throws Exception {
        //创建选择器
        Selector selector=Selector.open();
              //注册到选择器
            for(SocketChannel channel:channels) {
                channel.register(selector, SelectionKey.OP_CONNECT);
            }

            while (true){
                //获取就绪的通道，和服务器建立连接并准备开始传输数据
                int num=  selector.select();
                if(num>0){
               Iterator<SelectionKey> iterator= selector.selectedKeys().iterator();
               int i=0;
               while (iterator.hasNext()){
                     SelectionKey key=iterator.next();
                   SocketChannel channel= (SocketChannel) key.channel();
                 boolean bl= channel.finishConnect();//通道是否连接成功
                   if(bl){
                       //设置为读模式
                       key.interestOps(SelectionKey.OP_READ);
                       //创建一个缓冲区
                       ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                       //把通道的数据读到缓存区
                       channel.read(byteBuffer);
                       //打印出
                       if(byteBuffer.position()>0) {
                           System.out.println(new String(byteBuffer.array()) + i);
                       }
                       //设置为写模式
                       key.interestOps(SelectionKey.OP_WRITE);
                       //往服务器写
                       channel.write(Charset.forName("utf-8").encode("我是客户端"+i));
                       byteBuffer.clear();
                       i++;
                   }
                   iterator.remove();
               }
            }
       }
    }
}
