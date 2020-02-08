package com.sunxj.mysql;

//import org.apache.spark.api.r.StringRRDD;

public class UserDao {

    int id;
    String name;
    String password;
    String remark;
    public UserDao(int id, String name,String password,String remark) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.remark = remark;
    }
}
