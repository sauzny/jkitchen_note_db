package com.sauzny.dbutils.h2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class H2Manager {
    
    @Test
    public void foo01() throws SQLException, InterruptedException, ExecutionException{
        
        long a = System.currentTimeMillis();
        
        // 开始 mysql 操作
        
        Connection mysqlConn = JdbcUtils.mysqlConnect("demodata0.2");
        Object[][] resultorders = JdbcUtils.read(mysqlConn, "select * from orders");
        Object[][] resultproducts = JdbcUtils.read(mysqlConn, "select * from products");
        Object[][] resultperson = JdbcUtils.read(mysqlConn, "select * from person");
        
        long b = System.currentTimeMillis();
        
        //JdbcUtils.print(resultorders);
        
        // 开始 h2 操作
        
        String createsqlorders = "CREATE TABLE `orders` (`id` int(11),`status` int(11),`type` int(11),`productId` int(11),`personId` int(11),`createTime` datetime,`costPrice` double,`costScore` int(11),PRIMARY KEY (`id`));";
        String createsqlproducts = "CREATE TABLE `products` (`id` int(11),`name` varchar(255),`status` int(11),`nowPrice` double,`oldPrice` double,PRIMARY KEY (`id`));";
        String createsqlperson = "CREATE TABLE `person` (`id` int(11),`name` varchar(255),`age` int(11),`gender` varchar(255),`hometown` varchar(255),`phone` varchar(255),`email` varchar(255),`birthday` date,`balance` double,PRIMARY KEY (`id`));";
        
        Connection conn = JdbcUtils.h2memConnect("testdb");
        
        JdbcUtils.execute(conn, createsqlorders);
        JdbcUtils.execute(conn, createsqlproducts);
        JdbcUtils.execute(conn, createsqlperson);
        
        JdbcUtils.batch(conn, "insert into orders values (?,?,?,?,?,?,?,?)", resultorders);
        JdbcUtils.batch(conn, "insert into products values (?,?,?,?,?)", resultproducts);
        JdbcUtils.batch(conn, "insert into person values (?,?,?,?,?,?,?,?,?)", resultperson);
        
        long c = System.currentTimeMillis();

        JdbcUtils.read(conn, "select year(createTime), products.`status`, count(orders.id) from orders JOIN products ON orders.productId = products.id GROUP BY year(createTime), products.`status`");

        long d = System.currentTimeMillis();
        
        System.out.println("load耗时：" + (b-a));
        System.out.println("插入耗时：" + (c-b));
        System.out.println("查询耗时：" + (d-c));
        System.out.println("总耗时：" + (d-a));
    }      
    
    
}

