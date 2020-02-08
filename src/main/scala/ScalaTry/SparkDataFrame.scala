package ScalaTry

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object SparkDataFrame {

  def main(args: Array[String]): Unit = {
    /**
     * 使用的伪分布式
     *
     */
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    import spark.implicits._
//    val t1 = spark.createDataFrame(SparkContext.getOrCreate().parallelize(List(1,2,3,4)))
    val df= spark.read.json("file:///Users/4paradigm/Desktop/data.json")
    df.show()
      println("1")
    df.select("address").show()
    df.select(("location")).show()
    df.select($"address",$"location").show()
    df.filter($"age" <=21)
    df.groupBy("age").count().createGlobalTempView("ageGroup")
    df.createOrReplaceTempView("people")
    val sqlDF = spark.sql("SELECT * FROM people")
    sqlDF.show()
    spark.sql("select * from people left join global_temp.ageGroup on people.age=ageGroup.age").show()
    spark.sparkContext.parallelize(List(1,2,3,4)).foreach(println)
  }

}
