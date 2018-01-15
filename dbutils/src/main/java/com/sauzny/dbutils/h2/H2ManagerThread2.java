package com.sauzny.dbutils.h2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class H2ManagerThread2 {
    
    @Test
    public void foo01() throws SQLException, InterruptedException, ExecutionException{
        
        ExecutorService service = Executors.newFixedThreadPool(3);

        String createsqlOrders1 = "CREATE TABLE `Orders1` (`id` int(11),`status` int(11),`type` int(11),`productId` int(11),`personId` int(11),`createTime` datetime,`costPrice` double,`costScore` int(11),PRIMARY KEY (`id`));";
        String createsqlOrders2 = "CREATE TABLE `Orders2` (`id` int(11),`status` int(11),`type` int(11),`productId` int(11),`personId` int(11),`createTime` datetime,`costPrice` double,`costScore` int(11),PRIMARY KEY (`id`));";
        
        long a = System.currentTimeMillis();
        
        // 开始 mysql 操作
        /*
        Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
        Object[][] resultOrders = JdbcUtils.read(mysqlConn, "select * from Orders");
        Object[][] resultProducts = JdbcUtils.read(mysqlConn, "select * from Products");
        Object[][] resultPerson = JdbcUtils.read(mysqlConn, "select * from Person");
        
        */
        Future<?> futureOrders1 = service.submit(() ->{
            Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
            Object[][] resultOrders = JdbcUtils.read(mysqlConn, "select * from Orders");
            Connection conn = JdbcUtils.h2memConnect("testdb");
            JdbcUtils.execute(conn, createsqlOrders1);
            JdbcUtils.batch(conn, "insert into Orders1 values (?,?,?,?,?,?,?,?)", resultOrders);
        });
        
        Future<?> futureOrders2 = service.submit(() ->{
            Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
            Object[][] resultOrders = JdbcUtils.read(mysqlConn, "select * from Orders");
            Connection conn = JdbcUtils.h2memConnect("testdb");
            JdbcUtils.execute(conn, createsqlOrders2);
            JdbcUtils.batch(conn, "insert into Orders2 values (?,?,?,?,?,?,?,?)", resultOrders);
        });

        futureOrders1.get();
        futureOrders2.get();
        
        long b = System.currentTimeMillis();
        
        System.out.println("耗时：" + (b-a));
    }      
    
    
}

