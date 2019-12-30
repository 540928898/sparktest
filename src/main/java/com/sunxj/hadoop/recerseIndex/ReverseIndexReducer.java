package com.sunxj.hadoop.recerseIndex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReverseIndexReducer extends Reducer<Text,Text,Text,Text> {
    private Text outvalue = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//        int count = 0;
        StringBuffer sb1 = new StringBuffer();
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        for(Text value:values){
            sb1 = new StringBuffer();
            String[] temp = sb1.append(value.toString()).reverse().toString().split(":"); // 倒转 防止前面有冒号
            String path = new StringBuffer(temp[1].split("/")[0]).reverse().toString();
            int count = Integer.valueOf(temp[0]);
            System.out.println("readucepath!!!"+path);
            System.out.println(count);
            if (map1.containsKey(path)){
                map1.put(path, map1.get(path) + count);
            }else{
                map1.put(path, count);
            }
        }
        sb1.delete(0, sb1.length() - 1);
        for(Map.Entry<String,Integer> entry :map1.entrySet()){
            sb1.append(entry.getKey()).append(":").append(entry.getValue());
        }
        outvalue.set(new Text(sb1.toString()));
        context.write(key,outvalue);
    }
}
