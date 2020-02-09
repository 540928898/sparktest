package com.sunxj.leetcode.StringFolder;

import org.apache.spark.sql.execution.columnar.NULL;

import java.util.Stack;
//import scala.None;

public class simplifyPath {
    public static void main(String[] args) {
        String buffer = new String("/../");
        String[] t1 = buffer.split("/");
        Stack<String> t2 = new Stack<>();
        for (String tmp:t1
             ) {
            if(tmp.equals("") || tmp.equals(".")){
                continue;
            }
            else{
                if (tmp.equals("..")) {
                    if(t2.size() >= 1){
                        t2.pop();
                    }

                }
                else t2.push(tmp);
            }
        }
        StringBuffer sb = new StringBuffer();
        if(sb.length() == 0){
            System.out.println("/");
        }
        for (String tmp:t2
             ) {
            sb.append("/");
            sb.append(tmp);
        }
        System.out.println(sb);
    }
}
