package com.sunxj.sparktest
import org.apache.spark.{SparkConf, SparkContext}
object count {
  def word1(conf: SparkConf):Unit={
    //     ssc =  SparkContext(conf);
    new SparkContext(conf).parallelize(List(1,2,3,4,5)).map(x=>x*x).map(x=>x/2)
  }
  def main(args: Array[String]): Unit={
    val conf = new SparkConf()
      .setAppName("first spark app(scala)")
      .setMaster("local[1]");
    val ssc  =new SparkContext(conf);
    val tt = ssc.parallelize(Array(("A",1),("B",2),("C",3),("D",4))).flatMap(x=>(x._1+x._2))
    val data:String = "A;B;C;D;E;F;A;B;";
    ssc.parallelize(List(data.split(";"))).foreach(println)
    val rdd1 = ssc.parallelize(List(1,2,3,4))
    rdd1.map(x=>x match{case 3 => List(1,2)
    case _ => x*2}).foreach(println)
//    ssc.parallelize(Array((1),(1))).flatMap(x=>x.)
//    tt.collect().foreach(println);
//    ssc.addJar("~/opt/sparktest.jar")
//    new SparkContext(conf)
//      .parallelize(List(1,2,3,4,5,6))
//      .map(x=>x*x)
//      .filter(_>10)
//      .repartition(3)
//      .collect()
//      .foreach(println);
  }
}
