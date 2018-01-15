package com.sauzny.dbutils.h2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

public class H2Manager2 {
    
    @Test
    public void foo01() throws SQLException, InterruptedException, ExecutionException{
        
        long a = System.currentTimeMillis();
        
        // 开始 mysql 操作
        
        Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
        Object[][] resultOrders1 = JdbcUtils.read(mysqlConn, "select * from Orders");
        Object[][] resultOrders2 = JdbcUtils.read(mysqlConn, "select * from Orders");
        
        long b = System.currentTimeMillis();
        
        //JdbcUtils.print(resultOrders);
        
        // 开始 h2 操作
        
        String createsqlOrders1 = "CREATE TABLE `Orders1` (`id` int(11),`status` int(11),`type` int(11),`productId` int(11),`personId` int(11),`createTime` datetime,`costPrice` double,`costScore` int(11),PRIMARY KEY (`id`));";
        String createsqlOrders2 = "CREATE TABLE `Orders2` (`id` int(11),`status` int(11),`type` int(11),`productId` int(11),`personId` int(11),`createTime` datetime,`costPrice` double,`costScore` int(11),PRIMARY KEY (`id`));";

        Connection conn = JdbcUtils.h2memConnect("testdb");
        
        JdbcUtils.execute(conn, createsqlOrders1);
        JdbcUtils.execute(conn, createsqlOrders2);
        
        JdbcUtils.batch(conn, "insert into Orders1 values (?,?,?,?,?,?,?,?)", resultOrders1);
        JdbcUtils.batch(conn, "insert into Orders2 values (?,?,?,?,?,?,?,?)", resultOrders2);
        
        long c = System.currentTimeMillis();

        
        System.out.println("load耗时：" + (b-a));
        System.out.println("插入耗时：" + (c-b));
        System.out.println("总耗时：" + (c-a));
    }      
    
    
}

