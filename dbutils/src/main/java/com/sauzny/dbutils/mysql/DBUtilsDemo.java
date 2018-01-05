package com.sauzny.dbutils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DBUtilsDemo {

    private static String driveClassName = "com.mysql.cj.jdbc.Driver";  
    private static String url = "jdbc:mysql://192.168.73.128:3306/kula?useUnicode=true&characterEncoding=utf8&useSSL=false";   
    private static String user = "root";  
    private static String password = "root";  

    public static Connection Connect() {
        Connection conn = null;

        // load driver
        try {
            Class.forName(driveClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("load driver failed!");
            e.printStackTrace();
        }

        // connect db
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("connect failed!");
            e.printStackTrace();
        }

        return conn;
    }
    

    public static void insert_test(String sql) throws SQLException{  
        Connection conn = DBUtilsDemo.Connect();  
          
        //创建SQL执行工具   
        QueryRunner qRunner = new QueryRunner();   
          
        //执行SQL插入   
        int n = qRunner.update(conn, sql);   
        //int n = qRunner.update(conn, "insert into user(name,age) values('xxx',22)");   
        //System.out.println("成功插入" + n + "条数据！");   
          
        //关闭数据库连接   
        DbUtils.closeQuietly(conn);       
    }   
    
    public static Object selectScalarHandler(String sql) throws SQLException {  
        Connection conn = DBUtilsDemo.Connect();  
        
        //创建SQL执行工具   
        QueryRunner qRunner = new QueryRunner();   
          
        Object object = qRunner.query(conn, sql, new ScalarHandler<Object>());  
        
        //关闭数据库连接   
        DbUtils.closeQuietly(conn);   
        return object;
    }
    
    public static <T> List<T> select_bean_list(String sql, Class<T> clazz) throws SQLException{  
        Connection conn = DBUtilsDemo.Connect();  
          
        //创建SQL执行工具   
        QueryRunner qRunner = new QueryRunner();   
          
        List<T> list = qRunner.query(conn, sql, new BeanListHandler<T>(clazz));  
        //List<UserBean> list = (List<UserBean>) qRunner.query(conn, "select id,name,age from user", new BeanListHandler(UserBean.class));   
        //输出查询结果   
        /*
        for (UserBean user : list) {   
                System.out.println(user.getAge());   
        }   
        */  
        //关闭数据库连接   
        DbUtils.closeQuietly(conn);   
        return list;
    }   
  
    public static void update_test(String sql) throws SQLException{  
        Connection conn = DBUtilsDemo.Connect();  
          
        //创建SQL执行工具   
        QueryRunner qRunner = new QueryRunner();   
        //执行SQL插入   
        int n = qRunner.update(conn, sql);   
        //int n = qRunner.update(conn, "update user set name = 'xxx',age=28");   
        System.out.println("成功更新" + n + "条数据！");   
          
        //关闭数据库连接   
        DbUtils.closeQuietly(conn);   
    }   
      
    public static void del_test(String sql) throws SQLException{  
        Connection conn = DBUtilsDemo.Connect();  
          
        //创建SQL执行工具   
        QueryRunner qRunner = new QueryRunner();   
        //执行SQL插入   
        int n = qRunner.update(conn, sql);   
        //int n = qRunner.update(conn, "DELETE from user WHERE name='xxx';");   
        System.out.println("删除成功" + n + "条数据！");   
          
        //关闭数据库连接   
        DbUtils.closeQuietly(conn);   
    }   
}
