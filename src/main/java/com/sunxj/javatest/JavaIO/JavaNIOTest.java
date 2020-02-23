package com.sunxj.javatest.JavaIO;

import org.apache.spark.unsafe.types.ByteArray;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
/*
当一个线程提交了任务一直要等待 就是阻塞
否则就是非阻塞
传统IO是阻塞的  NIO是非阻塞的 阻塞非阻塞是 对于应用端的
同步和异步 是对于服务器来说的 也就是或 同步 是应用端需要不断询问 数据是否准备好 异步是由服务器通知

 */

public class JavaNIOTest {
    private static String infile = new String("/Users/4paradigm/Desktop/infile.txt");
    private static String outfile = new String(("/Users/4paradigm/Desktop/outfile.txt"));
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();
        ByteBuffer buff = ByteBuffer.allocate(1024);
        String sendString="你好,服务器. ";
        ByteBuffer sendBuffer = ByteBuffer.wrap(sendString.getBytes());
        fcin.read(buff);
        ByteArray arr1 = new ByteArray();

        buff.flip();
                while (buff.hasRemaining()) {
            System.out.print((char)buff.get());
        }
//读出
        System.out.print(Charset.forName("UTF-8").decode(sendBuffer).toString());
        fcout.write(sendBuffer);
    }



}
