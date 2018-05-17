package com.sauzny.neo4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// jdbc方式连接neo4j，还有 spring data neo4j，在spring demo中编写代码
public class Remote {

    /*
     * 
     * 建表sql
     * 
                创建一个球员节点恩比德
                
                ```
                CREATE (embiid: PLAYER {name: 'Embiid', height: '2.13m', nationality: 'Cameroon'}) 
                ```
                
                创建歌手蕾哈娜节点以及“喜欢”关系
                
                ```
                MATCH (embiid:PLAYER{name:'Embiid'})
                MERGE (embiid)-[:LIKES{since:'2014'}]->(Rihanna: SINGER{name:'Rihanna', dob:'1988/2/20',bloodType:'O'})
                ```
                
                查询返回所有喜欢蕾哈娜的PLAYER节点
                
                ```
                MATCH(p:PLAYER)-[:LIKES]->(:SINGER{name:'Rihanna'})
                RETURN p;
                ```
     */
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        Class.forName("org.neo4j.jdbc.Driver");
        
        Connection con = DriverManager
                .getConnection("jdbc:neo4j:http://localhost:7474/","neo4j","Database01");  //创建连接

        String query = "start n = node({1}) return n.name";

        PreparedStatement stmt = null;       //采用预编译，和关系数据库不一样的是,参数需要使用{1},{2},而不是?
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, 20);
            rs = stmt.executeQuery();
            System.out.println(rs.getRow());
            while (rs.next()) {
                System.out.println("a " + rs.getString("n.name"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != rs) {
                rs.close();
            }
            if (null != stmt) {
                stmt.close();
            }
        }
    }
}
