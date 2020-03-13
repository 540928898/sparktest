package com.sunxj.mysql;

import java.sql.*;

public class ConnectMysql {

    //所有的配置文件可以放到一起，像在维信金科的一样
    static final String name = "root";
    static final String password = "13815690318";
    static String jdbcPath = "jdbc:mysql://localhost:3306/gupengtest";

    public ConnectMysql(String jdbcPath1) throws SQLException {
        this.jdbcPath = jdbcPath1;
    }

    static Connection mysqlConnect;
    static {
        try {
            mysqlConnect = DriverManager.getConnection(jdbcPath, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ResultSet selectSQL(String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement statement = mysqlConnect.prepareStatement(sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    static boolean insertSQL(UserDao user1) {
        String sql = "insert into user (id,name,password,remark) values(9,'ds','mm','789')";
        try {
            PreparedStatement statement = mysqlConnect.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
    void layoutStyle2(ResultSet rs) {
        System.out.println("-----------------");
        System.out.println("执行结果如下所示:");
        System.out.println("-----------------");
        System.out.println(" 用户ID" + "  " + "姓名" + "  " + "密码"+ "  " + "remark");
        System.out.println("-----------------");
        try {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "  "
                        + rs.getString("name") + "  "
                        + rs.getString("password")
                        + "  "+ rs.getString("remark"));
            }
        } catch (SQLException e) {
            System.out.println("显示时数据库出错。");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("显示出错。");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        ConnectMysql conn1 = new ConnectMysql("jdbc:mysql://localhost:3306/gupengtest");
        ResultSet t1 = conn1.selectSQL("select * from user");
        conn1.layoutStyle2(t1);
//        t1.getString("name");

//        conn1.insertSQL(new UserDao(4,"1","2","3"));

    }
}
