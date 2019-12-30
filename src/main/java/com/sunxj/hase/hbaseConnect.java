package com.sunxj.hase;

import com.sunxj.hadoop.*;
import jersey.repackaged.com.google.common.collect.HashBasedTable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//TODO isRowExist
//TODO addAll
public class hbaseConnect {
    private static Configuration conf = HBaseConfiguration.create();
    private static Connection myconnection;
    public static HBaseAdmin admin; //这样是不太正确的   connection是线程安全的，admin不是线程安全的。

    public  void setMyconnection() throws IOException {
        myconnection = ConnectionFactory.createConnection(conf);
        admin =(HBaseAdmin) myconnection.getAdmin();
    }
    public static boolean isExist(String tableName){
        //对表操作需要使用HbaseAdmin
        try {
            //管理表
            return admin.tableExists(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void isRowExist(String tableName,String rowKey){
        System.out.println(1);
    }
    public static void createTable(String tableName,String... columnfamily){
        try {
            //管理表
            //1.表如果存在，请输入其他表名
            if(isExist(tableName)){
                System.out.println("表存在，请输入其他表名");
            }else{
                //2.注意:创建表的话，需要创建一个描述器
                HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));

                //3.创建列族
                for(String cf:columnfamily){
                    htd.addFamily(new HColumnDescriptor(cf));
                }
                //4.创建表
                admin.createTable(htd);
                System.out.println("表已经创建成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTable(String tableName) {
        try {
            //1.表如果存在，请输入其他表名
            if (!isExist(tableName)) {
                System.out.println("表不存在");
            } else {
                //2.如果表存在，删除 删除之前需要先禁用表
                admin.disableTable(TableName.valueOf(tableName));
                admin.deleteTable(TableName.valueOf(tableName));
                System.out.println("表删除了");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addRow(String tableName,String rowkey,String cf,String column,String value){
        try {
            //对表操作需要使用HbaseAdmin
            Table t = myconnection.getTable(TableName.valueOf(tableName));
            //1.表如果存在，请输入其他表名
            if (!isExist(tableName)) {
                System.out.println("表不存在");
            } else {
                //2.用put方式加入数据
                Put p = new Put(Bytes.toBytes(rowkey));
//                p.addColumn()
                //3.加入数据
//                t.checkAndPut()
                p.addColumn(Bytes.toBytes(cf),Bytes.toBytes(column),Bytes.toBytes(value));
                t.put(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void delrow(String tableName,String rowKey){
        try{
            Table t = myconnection.getTable(TableName.valueOf(tableName));
            if(isExist("tableName")) {
                System.out.println(tableName+" is not exists!");
            }
            else{
                Delete newDelete = new Delete(Bytes.toBytes(rowKey));
                t.delete(newDelete);
                System.out.println("Delete "+rowKey +" is success");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void readRow(String tableName,String rowKey) {
        try{
            if(!isExist(tableName)){
                System.out.println("Table "+tableName +" is not exists");
            }
            else {
                Table table = myconnection.getTable(TableName.valueOf(tableName));
                Get get = new Get(Bytes.toBytes(rowKey));
//                get.addColumn("liezuName")
                Result res = table.get(get);
                Cell[] cells = res.rawCells();
                if (cells.length == 0){
                    System.out.println("No key");
                }
                for (Cell c : cells){
                    System.out.print("行键为："+Bytes.toString(CellUtil.cloneRow(c))+"  ");
                    System.out.print("列族为："+Bytes.toString(CellUtil.cloneFamily(c))+"  ");
                    System.out.print("列名："+Bytes.toString(CellUtil.cloneQualifier(c))+"  ");
                    System.out.println("值为："+Bytes.toString(CellUtil.cloneRow(c))+"  ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void scanAll(String tableName,String colFamily,String qualifier, String rowKeyStart,String rowKeyEnd){
        try {
            if(!isExist((tableName))){
                System.out.println("Table "+tableName +" is not exists");
            }
            else {
                Scan scanner = new Scan();
                scanner.setStartRow(Bytes.toBytes(rowKeyStart));
                scanner.setStopRow(Bytes.toBytes(rowKeyEnd));
                Table table = myconnection.getTable(TableName.valueOf(tableName));
                scanner.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(qualifier));
                ResultScanner rs = table.getScanner(scanner);
//                Cell[] cells = rs.

                for (Result r:rs
                     ) {
                    Cell[] cells = r.rawCells();
                    for (Cell c:cells
                         ) {
                        System.out.print("行键为："+Bytes.toString(CellUtil.cloneRow(c))+"  ");
                        System.out.print("列族为："+Bytes.toString(CellUtil.cloneFamily(c))+"  ");
                        System.out.print("列名："+Bytes.toString(CellUtil.cloneQualifier(c))+"  ");
                        System.out.println("值为："+Bytes.toString(CellUtil.cloneValue(c))+"  ");
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        };

    }
    public void delAll(String tableName,String... rowKeys) throws IOException {
        try {
            if(!isExist((tableName))){
                System.out.println("Table "+tableName +" is not exists");
            }
            else {
                Table table = myconnection.getTable(TableName.valueOf(tableName));
                ArrayList<Delete> needAdd = new ArrayList<Delete>();
                for(String rowKey:rowKeys){
                    needAdd.add(new Delete(Bytes.toBytes(rowKey)));
                }
                table.delete(needAdd);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
//    public  void addAll(String tableName,String... rowKeys){
//        try{
//            if(!isExist(tableName)){
//                System.out.println("Table "+tableName +" is not exists");
//            }
//            else {
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    static class MTH_Mapper extends Mapper<LongWritable, Text, IntWritable, Text> {
//        IntWritable k = new IntWritable();
//        Text v = new Text();
//        @Override
//        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, Text>.Context context) throws IOException, InterruptedException, IOException {
//            String[] split = value.toString().split(",");
//            if (split.length == 5) {
//                k.set(Integer.parseInt(split[0]));
//                v.set(split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4] + "\t");
//                context.write(k, v);
//            }
//        }
//    }
//
//    static class MTH_Reduce extends TableReducer<IntWritable, Text, NullWritable> {
//        @Override
//        protected void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, NullWritable, Mutation>.Context context) throws IOException, InterruptedException {
//            for (Text v : values) {
//                String[] datas = v.toString().split("\t");
//                Put p = new Put((key.get() + "").getBytes());
//                p.addColumn("base_info".getBytes(), "name".getBytes(), datas[0].getBytes());
//                p.addColumn("base_info".getBytes(), "sex".getBytes(), datas[1].getBytes());
//                p.addColumn("base_info".getBytes(), "age".getBytes(), datas[2].getBytes());
//                p.addColumn("job_info".getBytes(), "dept".getBytes(), datas[3].getBytes());
//                context.write(NullWritable.get(), p);
//            }
//        }
//    }
    public static void main(String[] args) throws IOException {
//        System.out.println(isExist("emp"));
        hbaseConnect hb1 = new hbaseConnect();
        hb1.setMyconnection();
        System.out.println(hb1.isExist("emp"));
        hb1.createTable("emp",new String[] {"for","test"});
        hb1.addRow("emp","gupeng","for","name1","gugu");
        hb1.addRow("emp","gupeng1","for","name1","gugu");
        hb1.addRow("emp","gupeng2","test","name1","gugu");
        hb1.addRow("emp","gupeng3","for","name1","gugu");
        hb1.scanAll("emp","for","","gupeng","gupeng4");
        hb1.delAll("emp",new String[] {"gupeng","gupeng1","gupeng3"});
        hb1.scanAll("emp","for","","gupeng","gupeng4");
        hb1.deleteTable("emp");
        hb1.admin.close();
    }
}
