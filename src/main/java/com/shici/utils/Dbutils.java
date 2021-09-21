package com.shici.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * FileName: Dbutils
 * Description:
 * Author: CSH
 * Date: 2021/1/2 21:25
 * Version: 1.0
 */
public class Dbutils {

    private static Connection ct;

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mypoemsweb?serverTimezone=GMT%2B8&characterEncoding=uft-8&useSSL=false;";
        String user = "root";   //超级管理员
        String password = "root";  //密码

        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载驱动成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载驱动失败");
        }

        //2.连接
        try {
            ct = DriverManager.getConnection(url, user, password);
            System.out.println("连接数据库成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }

        /* 尝试添加数据到数据库中 */

        try {
            //在创建对象的基础上创建会话对象
            Statement stmt = ct.createStatement();
            //写插入数据的SQL语句
            String sql = "insert into t_user values('2','King','123')";
            //执行插入数据的SQL语句，返回受影响的行数
            int rs1 = stmt.executeUpdate(sql);
            //关闭会话对象
            stmt.close();
            /*
            * 如果受影响的行数大于零，则插入数据成功，
            * 返回true；否则插入数据失败，返回false
            * */
            if (rs1>0){
                System.out.println("插入成功");
            }else{
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }
}
