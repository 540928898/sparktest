package com.sunxj.hadoop.recerseIndex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;
//import sun.jvm.hotspot.memory.TenuredSpace;

import java.io.IOException;

public class ReverseIndexMapper extends Mapper<LongWritable, Text,Text, Text> {
    private Text word = new Text();
    private Text ovalue = new Text();
    private String filePath;


    @Override
    protected void map(LongWritable key,Text value,Context context)throws IOException,InterruptedException{
        String[] line = value.toString().split(",");
        FileSplit split = (FileSplit) context.getInputSplit();
        filePath = split.getPath().toString();
        for(String wordeach:line){
            word.set(wordeach);
            System.out.println("filePath!!"+filePath);
            ovalue.set(filePath+":1");
            context.write(word,ovalue);
        }
    }
}
