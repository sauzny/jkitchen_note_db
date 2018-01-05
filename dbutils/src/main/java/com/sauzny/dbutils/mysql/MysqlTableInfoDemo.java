package com.sauzny.dbutils.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class MysqlTableInfoDemo {

    public static void info(Connection conn){
        
        try {
            // 目录名字
            String catalogName = conn.getCatalog();
            
            ResultSet tableRs = conn.getMetaData().getTables(conn.getCatalog(), null, null, new String[] { "TABLE" });
            
            while (tableRs.next()) {
                // 获取TABLE_NAME
                String tableName = tableRs.getString("TABLE_NAME");
                System.out.println("<table name=\"table_01\" fullName=\""+ catalogName + "."+ tableName + "\">");
                ResultSet columnRs = conn.getMetaData().getColumns(conn.getCatalog(), "%", tableName, "%");
                while (columnRs.next()) {
                    // 获取COLUMN_NAME
                    String columnName = columnRs.getString("COLUMN_NAME").toLowerCase();
                    String typeName = columnRs.getString("TYPE_NAME").toLowerCase();
                    
                    String javaTpye = "error";
                    
                    if ("varchar".equals(typeName)) {
                        javaTpye = "java.lang.String";
                    }

                    if ("int".equals(typeName)) {
                        javaTpye = "java.lang.Integer";
                    }

                    if ("double".equals(typeName)) {
                        javaTpye = "java.lang.Double";
                    }
                    
                    if ("date".equals(typeName)) {
                        javaTpye = "java.sql.Date";
                    }
                    
                    System.out.println("<column name=\""+columnName+"\" dbType=\""+typeName+"\" javaType=\"" +javaTpye+ "\" />");
                }
                System.out.println("</table>");
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void printTableNames(Connection conn) throws SQLException{
        ResultSet tableRs = conn.getMetaData().getTables(conn.getCatalog(), null, null, new String[] { "TABLE" });
        
        for (int j = 1; j < tableRs.getMetaData().getColumnCount() + 1; j++) {
            System.out.print(tableRs.getMetaData().getColumnLabel(j) + "\t");
        }
        
        System.out.println();
        
        while(tableRs.next()){
            for (int j = 1; j < tableRs.getMetaData().getColumnCount() + 1; j++) {
                System.out.print(tableRs.getObject(j) + "\t");
            }
            System.out.println();
        }
        
    }

    public static void printColumnNames(Connection conn, String tableName) throws SQLException{
        
        ResultSet columnRs = conn.getMetaData().getColumns(conn.getCatalog(), "%", tableName, "%");
        
        for (int j = 1; j < columnRs.getMetaData().getColumnCount() + 1; j++) {
            System.out.print(columnRs.getMetaData().getColumnLabel(j) + "\t");
        }
        
        System.out.println();
        
        while(columnRs.next()){
            for (int j = 1; j < columnRs.getMetaData().getColumnCount() + 1; j++) {
                System.out.print(columnRs.getObject(j) + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        
        Connection conn = MysqlUtils.buildConn(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://172.16.1.25:3306/gquerydemo3?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&nullNamePatternMatchesAll=true",
                "root",
                "1"
                );
        
        try {
            //MysqlTableInfoDemo.printTableNames(conn);
            //MysqlTableInfoDemo.printColumnNames(conn, "country");
            MysqlTableInfoDemo.info(conn);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
