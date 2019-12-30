package com.sunxj.hadoop.wordCount;

import com.sunxj.hadoop.testHadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobSubmitter {
    public static void main(String[] args)throws Exception {
        /*
        Mapreduce 程序 路径：
        hdfs://localhost:8020/user/hadoop/mapreduce/output/
         */
        testHadoop fs = new testHadoop();
//        ToolRunnerTest test = new ToolRunner();

        fs.getFileSystem();
        fs.deleteFile("/user/hadoop/mapreduce/output/","");
        Configuration conf = new Configuration();
        Job job=Job.getInstance(conf,"wordCount");
        job.setJarByClass(JobSubmitter.class);
        job.setMapperClass(wordCountMapper.class);
        job.setCombinerClass(wordCountReducer.class);
        job.setReducerClass(wordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
//        FileInputFormat.addInputPath(job,in);
//        FileOutputFormat.setOutputPath(job,out);
        FileInputFormat.addInputPath(job, new Path("hdfs://localhost:8020/user/hadoop/mapreduce/input/newword.txt"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:8020/user/hadoop/mapreduce/output/"));
        System.exit(job.waitForCompletion(true)?0:1);
        /*
        查看结果
         */
        fs.listDir("/user/hadoop/mapreduce/output/");
//        fs.deleteFile("/user/hadoop/mapreduce/output/","");

    }
}
