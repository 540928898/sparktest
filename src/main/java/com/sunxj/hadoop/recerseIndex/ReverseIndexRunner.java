package com.sunxj.hadoop.recerseIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import com.sunxj.hadoop.testHadoop;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.URISyntaxException;

public class ReverseIndexRunner {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException {
        BasicConfigurator.configure();
        testHadoop fs = new testHadoop();
//        ToolRunnerTest test = new ToolRunner();

        fs.getFileSystem();
        fs.deleteFile("/user/hadoop/mapreduce/output/","");
//        fs.deleteFile("/user/hadoop/mapreduce/input/out","");
        Configuration conf = new Configuration();
        Job job=Job.getInstance(conf,"reverseIndex");
        job.setJarByClass(ReverseIndexRunner.class);
        job.setMapperClass(ReverseIndexMapper.class);
        job.setCombinerClass(ReverseIndexReducer.class);
        job.setReducerClass(ReverseIndexReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
//        FileInputFormat.addInputPath(job,in);
//        FileOutputFormat.setOutputPath(job,out);
        FileInputFormat.setInputPaths(job, new Path("hdfs://localhost:9000/user/hadoop/mapreduce/input/*/"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/user/hadoop/mapreduce/output/"));
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
