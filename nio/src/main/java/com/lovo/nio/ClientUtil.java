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
        Selector selector=Selector.open();
              //注册到选择器
            for(SocketChannel channel:channels) {
                channel.register(selector, SelectionKey.OP_CONNECT);
            }

            while (true){
                //获取就绪的通道
                int num=  selector.select();
                if(num>0){
               Iterator<SelectionKey> iterator= selector.selectedKeys().iterator();
               int i=0;
               while (iterator.hasNext()){
                     SelectionKey key=iterator.next();
                   SocketChannel channel= (SocketChannel) key.channel();
                 boolean bl= channel.finishConnect();//通道是否连接成功

                   if(bl){
                       key.interestOps(SelectionKey.OP_READ);
                       ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                       StringBuffer stringBuffer=new StringBuffer();

                           int len = 0;
                           while ((len = channel.read(byteBuffer)) > 0) {

                               stringBuffer.append(Charset.forName("utf-8").decode(byteBuffer).toString());

                           }
                           if(!"".equals(stringBuffer.toString())) {
                               System.out.println(new String(byteBuffer.array()));
                               key.interestOps(SelectionKey.OP_WRITE);

                           }

                           channel.write(Charset.forName("utf-8").encode("我是客户端"+i));

                           i++;
                       }

                   iterator.remove();
               }
            }

       }
    }
}
