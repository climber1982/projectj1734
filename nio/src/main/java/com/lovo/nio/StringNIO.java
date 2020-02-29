package com.lovo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class StringNIO {
    public static void main(String[] args) throws Exception {
        FileOutputStream outputStream=new FileOutputStream("E:/SVN/1.txt",true);
       FileChannel channel= outputStream.getChannel();
       ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
       String str="aaaaaa我很好!!";
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
       channel.write(byteBuffer);
    }

    public void read() throws Exception {
        FileInputStream fileInputStream=new FileInputStream("E:/SVN/1.txt");
        FileChannel channel=fileInputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        int len=-1;
        while((len=channel.read(byteBuffer))!=-1){
            byteBuffer.flip();
            String str=   Charset.forName("gbk").decode(byteBuffer).toString();
            System.out.print(str);

            byteBuffer.clear();
        }
    }
}
