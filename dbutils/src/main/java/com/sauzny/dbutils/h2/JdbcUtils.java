package com.sauzny.dbutils.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class JdbcUtils {

    public static Connection h2memConnect(String DBName) {

        String driveClassName = "org.h2.Driver";
        String url = "jdbc:h2:mem:" + DBName + ";DB_CLOSE_DELAY=-1";
        String user = "sa";
        String password = "";

        return JdbcUtils.connect(driveClassName, url, user, password);
    }

    public static Connection mysqlConnect(String DBName) {

        String driveClassName = "com.mysql.cj.jdbc.Driver";  
        String url = "jdbc:mysql://192.168.73.128:3306/"+DBName+"?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";   
        String user = "root";  
        String password = "root";

        return JdbcUtils.connect(driveClassName, url, user, password);
    }
    
    public static Connection connect(String driveClassName, String url, String user, String password) {
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

    public static void close(Connection conn) {
        try {
            DbUtils.close(conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void execute(Connection conn, String sql) {

        try{
            QueryRunner qr = new QueryRunner();
            qr.execute(conn, sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void batch(Connection conn, String sql, Object[][] params) {
        try{

            QueryRunner qr = new QueryRunner();
            qr.batch(conn, sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Object[][] read(Connection conn, String sql) {

        Object[][] result = null;

        try {

            QueryRunner qr = new QueryRunner();
            List<Object[]> list = qr.query(conn, sql, new ArrayListHandler());
            
            result = new Object[list.size()][list.get(0).length];
            
            result = list.toArray(result);
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println(e.getMessage());
        }
        
        return result;

    }
    
    public static void print(Object[][] params){
        
        for(int i=0;i<params.length;i++){
            for(int j=0;j<params[i].length;j++){

                System.out.print(params[i][j]+"\t");
            }
            System.out.println();
        }
    }
    /*
    public static Object[][] read(Connection conn, String sql) {

        Object[][] result = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            
            
            ResultSet rs = ps.executeQuery();

            // 移到最后一行
            rs.last();
            // 得到当前行号，也就是记录数
            int rowCount = rs.getRow();
            // 如果还要用结果集，就把指针再移到初始化的位置
            rs.beforeFirst();

            // 获得结果集结构信息,元数据
            ResultSetMetaData md = rs.getMetaData();
            // 获得列数
            int columnCount = md.getColumnCount();
            result = new Object[rowCount][columnCount];
            for (int i = 0; rs.next(); i++) {
                for (int j = 1; j <= columnCount; j++) {
                    result[i][j - 1] = rs.getObject(j);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }
     */

    public static void test(){
    }

}
