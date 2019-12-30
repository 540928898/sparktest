package com.sunxj.hadoop;
import com.esotericsoftware.minlog.Log;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
public class testHadoop {
    FileSystem fileSystem;
    String hdfsUri = "hdfs://localhost:8020";
//    Configured
    public  void   getFileSystem() throws IOException, URISyntaxException, InterruptedException{
        Configuration configuration = new Configuration();
        // 可通过指定的方式来获取，也可通过加载core-site.xml来获取
        // hxw用户为超级用户
        fileSystem = FileSystem.get(new URI(hdfsUri),configuration);
    }
    public void createDir(String path) throws IOException{
        Path dir = new Path(path);
        fileSystem.mkdirs(dir);
    }
    public void createFile(String createPath,String docName) throws IOException{
        Path path = new Path(createPath+docName);
        FSDataOutputStream out = fileSystem.create(path);
        String data = "I believe, for every drop of rain that falls, A flower grows";
        out.writeChars(data);
    }
    public void deleteFile(String delpath,String delname) throws IllegalArgumentException, IOException{
        Path path = new Path(delpath+delname);

        if(fileSystem.exists(path)){
            System.out.println("Now i will delete "+delname);
            fileSystem.delete(path, true);// 循环删除文件夹
        }else{
            System.out.println(path.getName() + " is not exists");
        }
    }
    public void readFile(String readPath,String readName,int isSeek) throws IOException{
        /**
         * isSeek: 去掉空格的输出方法和原始数据直接输出方法（有乱码  unicode 0： 是一个方框）
         */
        Path readpath = new Path(readPath + readName);
        if (fileSystem.exists(readpath)){
            System.out.println("Begin Read");
            FSDataInputStream in =null;
            try{
                if (isSeek == 0) {
                    in = fileSystem.open(readpath);
                    ByteBuffer readBuff = ByteBuffer.allocate(1000);
                    int read = 0;
                    while ((read = in.read(readBuff)) != -1) {
                        System.out.println(read);
                        String temp1 = new String(readBuff.array());
//                    System.out.println(new String(readBuff.array()));
                        for (int i = 0; i < temp1.length(); i++) {
                            if (temp1.charAt(i) == '\u0000') {
                                continue;
                            } else {
                                System.out.print(temp1.charAt(i));
                            }
                        }
                        readBuff.clear();
                    }
                }
                else {
                    IOUtils.copyBytes(in, System.out, 4096, false);
                    for (int i=1;i<=1;i++){
                        in.seek(0+20*(i-1));
                        System.out.println("流定位第 "+i+" 次：" );
                        IOUtils.copyBytes(in, System.out,4096,false);
                }
                }
            }
            finally {
                IOUtils.closeStream(in);
            }
        }
        else{
            System.out.println("Not exist readFile");
        }
    }
    public void writeFile(String filePath,String fimeName,String content,int isAppend){
        System.out.println(1);
    }
    public void listDir(String filePath) throws IOException {
        /**
         * 列出当前目录
         */
        Path dirpath = new Path(filePath);
        try{
        FileStatus[] dir = fileSystem.listStatus(dirpath);
        for (int i = 0; i<dir.length; i++) {
            System.out.println(dir[i].getPath().toString());
        }
        }
        catch (Exception FileNotFoundException) {
            System.out.println("No path");
            }
    }
    public  void getAllFile(String filePath) throws IOException {
        /**
         * 列出所有的文件
         */
        Path path = new Path(filePath);
        FileStatus[] fileStatus = fileSystem.listStatus(path);
        for(int i=0;i<fileStatus.length;i++){
            if(fileStatus[i].isDir()){
                getAllFile(fileStatus[i].getPath().toString());
            }else{
                System.out.println(fileStatus[i].getPath().toString());
            }
        }
    }

    public void loadFile(String loadPath,String targetPath) throws IOException {
        System.out.println(1);
        Path tar = new Path(targetPath);
        createDir(targetPath);
        try {
            fileSystem.copyFromLocalFile(new Path(loadPath), tar);
        }
        catch (Exception e){
            System.out.println("We got unexpected: " + e.getMessage());
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        testHadoop test1 = new testHadoop();
        test1.getFileSystem();
//        test1.readFile("/user/hadoop/mapreduce/input/","gu0301.txt",0);
//        test1.readFile("/user/hadoop/mapreduce/input/","wordcount.txt",0);
        for (int i = 0; i < 4 ; i++) {
            test1.readFile("/user/hadoop/mapreduce/output/","part-r-0000"+i,0);
        }
//        test1.listDir("/user/hadoop/mapreduce/input/");
//        test1.loadFile("/Users/4paradigm/Desktop/gu0301.txt","/user/hadoop/mapreduce/input/");
//        test1.loadFile("/Users/4paradigm/Desktop/newword.txt","/user/hadoop/mapreduce/input/");

    }
}

