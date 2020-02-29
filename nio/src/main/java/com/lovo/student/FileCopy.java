package com.lovo.student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopy {
    public static void main(String[] args) throws Exception {
        //获得文件的输入流
        FileInputStream fileInputStream=new FileInputStream("D:\\img\\zookeeper-3.4.12.tar.gz");
        //获得文件的输出流
        FileOutputStream fileOutputStream=new FileOutputStream("E:\\img\\zookeeper-3.4.12.tar.gz");

        //获取输入通道
       FileChannel inputChannel= fileInputStream.getChannel();
        //获取输出通道
        FileChannel outputChannel= fileOutputStream.getChannel();

        //创建缓存区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        int len=-1;
        while ((len=inputChannel.read(byteBuffer))!=-1){
            System.out.println("position="+byteBuffer.position()+"/limit="+byteBuffer.limit()+"/capacity"+byteBuffer.capacity());
            //跳转缓冲区
            byteBuffer.flip();
            System.out.println("position="+byteBuffer.position()+"/limit="+byteBuffer.limit()+"/capacity"+byteBuffer.capacity());
             //把缓冲区的内容写到输出通道中
            outputChannel.write(byteBuffer);
            //还原缓冲区
            byteBuffer.clear();
        }

          //关闭
        outputChannel.close();
        inputChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
    }

}
