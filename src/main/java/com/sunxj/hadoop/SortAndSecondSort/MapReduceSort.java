package com.sunxj.hadoop.SortAndSecondSort;

import com.sunxj.hadoop.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.BasicConfigurator;
import sun.net.TelnetInputStream;

import javax.naming.Context;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.TrustAnchor;

public class MapReduceSort extends Configured implements Tool {
    String hdfsUri = "hdfs://localhost:8020";
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        ToolRunner.run(new MapReduceSort(), args);

    }
//
//
//    private static void print(Tool newTool) throws IOException, URISyntaxException, InterruptedException {
////        FileSystem fs = FileSystem.get(newTool.getConf());
////        Path path = new Path("/user/hadoop/mapreduce/output/part-r-00000");
//        testHadoop hp1 = new testHadoop();
//        hp1.getFileSystem();
//        hp1.readFile("/user/hadoop/mapreduce/output/part-00000","",0);
//    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "SecondSort");
        job.setJarByClass(getClass());
        job.setMapperClass(Mymap.class);
        job.setReducerClass(Myreducer1.class);
//        job.setCombinerClass(Myreducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//分区
        job.setNumReduceTasks(4);
        job.setPartitionerClass(Mypartition.class);
        // 排序
        job.setSortComparatorClass(KeyComparator.class);
//

        FileSystem fs = FileSystem.get(new URI(hdfsUri),conf);
        fs.delete(new Path("hdfs://localhost:8020/user/hadoop/mapreduce/output"),true);
        FileInputFormat.addInputPath(job,new Path("hdfs://localhost:8020/user/hadoop/mapreduce/input/gu0301.txt/"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://localhost:8020/user/hadoop/mapreduce/output/"));
        System.exit(job.waitForCompletion(true)?0:1);
        return 0;

    }
}
class Mymap extends Mapper<LongWritable, Text,Text, IntWritable> {
    private Text myKey = new Text();
    private IntWritable myValue = new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strs = value.toString().split(" ");
        System.out.println("guguguug: "+strs[0]+"  value : "+strs[1]);
            myKey.set(new Text(strs[0]));
//            myValue.set(Integer.valueOf(strs[1]));
           context.write(myKey, new IntWritable(Integer.valueOf(strs[1])));
    }
}
class  Myreducer extends Reducer<Text,IntWritable,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("[");
        for (IntWritable it:values
             ) {
            sb1.append(it);
            sb1.append("_");
        }
        sb1.append("]");
        context.write(key, new Text(sb1.toString()));
    }
}

class  Myreducer1 extends Reducer<Text,IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable it:values
        ) {
           context.write(key,it);
        }
    }
}
class Mypartition extends Partitioner{
    @Override
    public int getPartition(Object key, Object value, int partitionnum) {
        if(key.toString().startsWith("aaa")) return 1;
        if(key.toString().startsWith("bbb")) return 2;
        if(key.toString().startsWith("ccc")) return 3;
        else return 0;
    }
}

class KeyComparator extends WritableComparator{
    public KeyComparator(){
        super(Text.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
       String t1 = a.toString();
       String t2 = b.toString();
       return t2.compareTo(t1);
    }
}