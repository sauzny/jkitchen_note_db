package com.sauzny.dbutils.h2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;


public class H2ManagerThread {
    
    @Test
    public void foo01() throws SQLException, InterruptedException, ExecutionException{
        
        ExecutorService service = Executors.newFixedThreadPool(3);

        String createsqlOrders = "CREATE TABLE `Orders` (`id` int(11),`status` int(11),`type` int(11),`productId` int(11),`personId` int(11),`createTime` datetime,`costPrice` double,`costScore` int(11),PRIMARY KEY (`id`));";
        String createsqlProducts = "CREATE TABLE `Products` (`id` int(11),`name` varchar(255),`status` int(11),`nowPrice` double,`oldPrice` double,PRIMARY KEY (`id`));";
        String createsqlPerson = "CREATE TABLE `Person` (`id` int(11),`name` varchar(255),`age` int(11),`gender` varchar(255),`hometown` varchar(255),`phone` varchar(255),`email` varchar(255),`birthday` date,`balance` double,PRIMARY KEY (`id`));";
        
        long a = System.currentTimeMillis();
        
        // 开始 mysql 操作
        /*
        Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
        Object[][] resultOrders = JdbcUtils.read(mysqlConn, "select * from Orders");
        Object[][] resultProducts = JdbcUtils.read(mysqlConn, "select * from Products");
        Object[][] resultPerson = JdbcUtils.read(mysqlConn, "select * from Person");
        
        */
        Future<?> futureOrders = service.submit(() ->{
            Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
            Object[][] resultOrders = JdbcUtils.read(mysqlConn, "select * from Orders");
            Connection conn = JdbcUtils.h2memConnect("testdb");
            JdbcUtils.execute(conn, createsqlOrders);
            JdbcUtils.batch(conn, "insert into Orders values (?,?,?,?,?,?,?,?)", resultOrders);
        });
        
        Future<?> futurePerson = service.submit(() ->{
            Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
            Object[][] resultProducts = JdbcUtils.read(mysqlConn, "select * from Products");
            Connection conn = JdbcUtils.h2memConnect("testdb");
            JdbcUtils.execute(conn, createsqlProducts);
            JdbcUtils.batch(conn, "insert into Products values (?,?,?,?,?)", resultProducts);
        });
        
        Future<?> futureProducts = service.submit(() ->{
            Connection mysqlConn = JdbcUtils.mysqlConnect("demodata");
            Object[][] resultPerson = JdbcUtils.read(mysqlConn, "select * from Person");
            Connection conn = JdbcUtils.h2memConnect("testdb");
            JdbcUtils.execute(conn, createsqlPerson);
            JdbcUtils.batch(conn, "insert into Person values (?,?,?,?,?,?,?,?,?)", resultPerson);
        });

        futureProducts.get();
        futurePerson.get();
        futureOrders.get();
        
        // 开始 h2 操作
        
        Connection conn = JdbcUtils.h2memConnect("testdb");
        
        JdbcUtils.read(conn, "select year(createTime), Products.`status`, count(Orders.id) from Orders JOIN Products ON Orders.productId = Products.id GROUP BY year(createTime), Products.`status`");

        long b = System.currentTimeMillis();
        
        System.out.println("耗时：" + (b-a));
    }      
    
    
}

