package com.sauzny.dbutils.h2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

import com.sauzny.dbutils.entity.LogEntity;
import com.sauzny.dbutils.entity.tools.Entity2DDL;



public class H2Test {
    
    // @Test
    public void logTest() throws IOException {

        Path path = Paths.get("E:/data/log/sparkbaby/demo.2017-09-22.0.log");

        List<String> list = Files.readAllLines(path);

        Object[][] params = new Object[list.size()][2];

        for (int j = 0; j < list.size(); j++) {
            params[j] = list.get(j).split(" - ");
        }

        Connection conn = JdbcUtils.h2memConnect("testdb");

        // String createsql = "CREATE TABLE `report_copy` (`name1` varchar(255) NOT NULL DEFAULT
        // '',`name2` varchar(255) NOT NULL DEFAULT '')";

        String createsql = Entity2DDL.entity2DDL(LogEntity.class);

        long a = System.currentTimeMillis();

        JdbcUtils.execute(conn, createsql);

        JdbcUtils.batch(conn, "insert into report_copy(name1,name2) values(?,?)", params);

        // int count = Integer.parseInt(JdbcUtils.selectScalarHandler(conn, "select count(1) from
        // report_copy").toString());

        // List<Temp> tempList = JdbcUtils.select_bean_list(conn, "select * from report_copy limit
        // 20 offset 10", Temp.class);

        long b = System.currentTimeMillis();

        System.out.println(b - a);

        // System.out.println(count);

        // tempList.forEach( System.out::println );

        JdbcUtils.close(conn);
    }
}
