package com.shici.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * FileName: Jdbcutils
 * Description:
 * Author: CSH
 * Date: 2020/12/23 19:10
 * Version: 1.0
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {

        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取数据库中的连接
     * @Return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     *
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if (conn == null) {

            try {
                conn = dataSource.getConnection();   //从数据库连接池中获取连接
                conns.set(conn);    //保存到ThreadLocal对象中，供后面的jdbc对象使用
                conn.setAutoCommit(false); //设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return conn;
    }

    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){//如果不等于null，说明之前使用过连接，操作过数据库

            try {
                connection.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        conns.remove();
    }



}
