package com.sunxj.HiveTools;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class ConnectHive {


    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark Hive")
                .master("local[2]")
                .getOrCreate();
        spark.sql("create table gupeng.gupengtest").show();

    }
}
