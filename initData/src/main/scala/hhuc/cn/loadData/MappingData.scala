package hhuc.cn.loadData

import java.text.SimpleDateFormat
import java.util

import org.apache.derby.iapi.util.StringUtil
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.catalyst.util.StringUtils
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession}

import scala.collection.mutable.ListBuffer


object MappingData {
  /**
   * 测试功能
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val data = new MappingData()
    val dateFormat = new SimpleDateFormat("HHmmss")
    while (true){
      var sql = "select * from initdata where time="
      var currentTime = System.currentTimeMillis()
      val datas: util.ArrayList[String] = data.loadCurrentDatasFromHive(sql, currentTime)
      if (datas != null){
        val interator: util.Iterator[String] = datas.iterator()
        while (interator.hasNext){
          println(interator.next())
        }
      }
      while (dateFormat.format(currentTime) == dateFormat.format(System.currentTimeMillis())){}
    }
  }

}

/**
 * 通过initdata中的lac_id、cell_id查询stationdata中的经纬度
 * @param database 所要操作hive中的数据库
 */
class MappingData(database:String = "cleardata"){
  // 创建sparkSQL环境
  private val sparkSession: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("mappingData")
    .config("hive.metastore.uris", "thrift://47.103.136.112:9083") // 设置hive元数据的路径
    .enableHiveSupport() // 提供hive支持
    .getOrCreate()

  // 设置日志级别
  sparkSession.sparkContext.setLogLevel("ERROR")
  // 设置时间展示格式
  private val dateFormat = new SimpleDateFormat("HHmmss")
  // 定义一个ArrayList来存储同一时刻的信息
  private var array:util.ArrayList[String] = null
  // 在构造方法中完成初始化工作
  init(database)

  /**
   * 定义私有初始化函数，该方法主要是选择使用hive中的哪个数据库
   * @param database 要操作hive中的数据库
   */
  private def init(database:String) :Unit = {
    // 设置SQL语句
    sparkSession.sql("show databases").show()
    val sql = "use " + database
    // 执行SQL语句
    sparkSession.sql(sql)
    sparkSession.sql("show tables").show()
//    sparkSession.sql("select * from test where num = 2").show()
  }

  /**
   * 该方法的功能是通过SQL和当前时间查询信息
   * @param sql 要执行的SQL语句
   * @param currentTime 当前时间
   * @return
   */
  def loadCurrentDatasFromHive(sql:String,currentTime:Long) : util.ArrayList[String] = {
    // 给ArrayList分配内存
    array = new util.ArrayList[String]()
    // 提取当前时间的时分秒，然后根据主办方提供的数据时间构造时间
    val time: String = 20181003 + dateFormat.format(currentTime)
//     val time = "20181003225328"
    // 将SQL语句和时间拼接
//    var SQL = sql + "20181003225328"
    println("============================================")
    var SQL = "select * from initdata where time = " + time
    println(SQL)
    // 执行SQL语句
    val initDataFrame: DataFrame = sparkSession.sql(SQL)
    println("finish")
    // 判断原始数据中是否存在当前时刻的数据，没有即直接返回，有就通过lac_id和cell_id查询stationdata中的经纬度
    if (initDataFrame.collect().isEmpty){  //initDataFrame.collect().isEmpty
      println("no data")
      return null
    }else{
      println("find data")
      // 初始化结果为空串
      var res = ""
      // 获取initdata中的time\imsi\lac_id\cell_id字段
      val lacell: DataFrame = initDataFrame.select("time","imsi","lac_id", "cell_id")
      // 将其收集成Array
      val rows: Array[Row] = lacell.collect()
      // 遍历Array
      for (row <- rows) {
        // 结果构建
        res = row.get(0) + " " + row.get(1) + " " + "null" + " "
        // 拼接查询条件
        val lacellField = "'" + row.get(2) + "-" + row.get(3) + "'"
        // 将查询条件与查询语句拼接
        val SQL = "select * from stationdata where lacell=" + lacellField
        // 执行SQL语句
        val stationDataFrame: DataFrame = sparkSession.sql(SQL)
        // 获取需要的lng\lat字段
        stationDataFrame.select("lng","lat").collect().foreach(row => {
          // 结果构建
          res = res + row.get(0) + " " + row.get(1)
//          println(res)
        })
        // 将同一时刻的结果放入ArrayList中
        array.add(res)
      }
      println(array)
//      println("============================================")
      // 返回同一时刻的查询信息
      return array
    }
  }

  /**
   * 将得到的查询信息加入到Iteractor中
   * @param tableName 操作的表名
   * @param currentTime 当前时间
   * @return
   */
  def loadDatasToIterator(tableName:String="initdata",currentTime:Long): util.Iterator[String] ={
    // 构造查询语句
    val sql = "select * from " + tableName + " where time="
    // 调用loadCurrentDatasFromHive()方法获得查询结果
    val currentdatas: util.ArrayList[String] = loadCurrentDatasFromHive(sql, currentTime)
    // 判断结果是否为null，不为null的话，将其变成Iteractor并返回
    if (currentdatas != null){
      val interator: util.Iterator[String] = currentdatas.iterator()
      return interator
    }
    return null;
  }
}
