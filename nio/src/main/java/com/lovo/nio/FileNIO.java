package com.lovo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIO {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("E:/SVN/1.doc");

        FileOutputStream outputStream=new FileOutputStream("D:\\img\\1.doc");

       FileChannel channel= fileInputStream.getChannel();
       FileChannel outChannel=outputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        int len=0;
       while ((len=channel.read(byteBuffer))!=-1){

           byteBuffer.flip();
           outChannel.write(byteBuffer);
           byteBuffer.clear();
       }

       outChannel.close();
       channel.close();
       outputStream.close();
       fileInputStream.close();

    }
}
