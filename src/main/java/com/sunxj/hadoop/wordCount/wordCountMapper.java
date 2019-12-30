package com.sunxj.hadoop.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class wordCountMapper extends Mapper<LongWritable,Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key,Text value,Context context)throws IOException,InterruptedException{
        String line = value.toString();
        String[] words = line.split(",");
        Counter sensitiveCounter = context.getCounter("Sensitive Words:", "Hello");
        if(line.contains("男")){
            sensitiveCounter.increment(1L);
        }
        for(String word:words){
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
