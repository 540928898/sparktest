package ScalaTry

import org.apache.spark.sql.SparkSession

object SparkDataSets {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    import spark.implicits._
    val caseClassDS = Seq(Person("Andy", 32)).toDS()
    caseClassDS.show()
    val primitiveDS = Seq(1, 2, 3).toDS()
    primitiveDS.map(_+1).collect().foreach(print)
    //RDD转DS
    spark.sparkContext.textFile("file:///Users/4paradigm/Desktop/people.txt")
      .map(_.split(","))
      .map(agg=>Person(agg(0),agg(1).trim.toInt))
      .toDF().createOrReplaceTempView("people")
    val dsSql = spark.sql("select * from people where age <= 13")
    dsSql.show()
    dsSql.map(tee=>"name:"+tee(0)).show()
    spark.udf.register("myAverage",Myaverage)
    val udfRes = spark.sql("select name,myAverage(age) as avgAge from people group by name")
    udfRes.show()
  }
}
case class Person(name: String, age: Long)
