package com.sunxj.javatest.JVM;

public class StringTest {
    public static void main(String[] args) {
        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";//常量池中的对象
        String str4 = str1 + str2; //在堆上创建的新的对象
        String str5 = "string";//常量池中的对象
        String str6 = new String("string");
        String str7 = new String(str4);
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false
        System.out.println(str4 == str6);//false
        System.out.println(str7 .equals(str6));//false


    }
}
