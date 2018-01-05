package com.sauzny.dbutils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MysqlUtils {

    private MysqlUtils(){}
    
    public static Connection buildConn(String... args){
        if(args.length != 4){
            System.err.println("参数错误");
            return null;
        }
        return buildConn(args[0],args[1],args[2],args[3]);
    }
    
    public static Connection buildConn(
            String driveClassName,
            String url,
            String user,
            String password
            ){
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
}
