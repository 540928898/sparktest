package ScalaTry
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object ScalaInit {
  def Print1(s:String)={
    print(s)
  }
  def Print2(rdd:RDD[String])={
    rdd.map(Print1)

  }
//  def main(args: Array[String]): Unit = {
//
    val conf = new SparkConf()
      .setAppName("first spark app(scala)")
      .setMaster("local[1]")
    val sc = new SparkContext(conf)
//    val r1 = sc.parallelize(List(1,2,3,4,5))
//    r1.map(s=>(s._1,s._2)).foreach(println())

//    val path = "file:///Users/4paradigm/Desktop/"
//    val lines = sc.textFile(path+"data.txt")
//    lines.repartition(2).collect().foreach(println)
//    val lineLengths = lines.map(s => s.length)
//    val totalLength = lineLengths.reduce((a, b) => a + b)
//    val data = Array(1, 2, 3, 4, 5)
//    val distData = sc.parallelize(data)
//    distData.foreach(println)
//    print(totalLength)
//    var counter = 0
//    val data1 = List(1,2,3,4)
//    var rdd = sc.parallelize(data1)
//    rdd.foreach(println)
//    rdd.foreach(x=>counter += x)
//    println(counter)


//  }
}
