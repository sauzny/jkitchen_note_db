package com.sauzny.neo4j.w3cschool;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class TPNeo4jDB {

    public static void main(String[] args) {
        
        File storeDir = new File("target/TPNeo4jDB");
        
        // 2. 创建Neo4j数据库
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService db= dbFactory.newEmbeddedDatabase(storeDir);
        
        // 3. 启启动Neo4j数据库事务以提交我们的更改
        try (Transaction tx = db.beginTx()) {
            // Perform DB operations  

            // 4. 要创建节点，我们需要标签名称。 通过实现Neo4j Java API“Label”接口创建一个枚举。
            /*
                import org.neo4j.graphdb.Label;
                public enum Tutorials implements Label {
                    JAVA,SCALA,SQL,NEO4J;
                } 
             */
            
            // 5. 创建节点并为其设置属性
            
            Node javaNode = db.createNode(Tutorials.JAVA);
            Node scalaNode = db.createNode(Tutorials.SCALA);
            
            javaNode.setProperty("TutorialID", "JAVA001");
            javaNode.setProperty("Title", "Learn Java");
            javaNode.setProperty("NoOfChapters", "25");
            javaNode.setProperty("Status", "Completed");    
                
            scalaNode.setProperty("TutorialID", "SCALA001");
            scalaNode.setProperty("Title", "Learn Scala");
            scalaNode.setProperty("NoOfChapters", "20");
            scalaNode.setProperty("Status", "Completed");
            
            // 6. 要创建关系，我们需要关系类型。 通过实现Neo4j“关系类型”创建枚举。
            /*
                package com.tp.neo4j.java.examples;
                import org.neo4j.graphdb.RelationshipType;
                public enum TutorialRelationships implements RelationshipType{
                    JVM_LANGIAGES,NON_JVM_LANGIAGES;
                }
            */
            
            // 7. 创建节点之间的关系并设置它的属性。

            Relationship relationship = javaNode.createRelationshipTo(scalaNode, TutorialRelationships.JVM_LANGIAGES);
            
            relationship.setProperty("Id","1234");
            relationship.setProperty("OOPS","YES");
            relationship.setProperty("FP","YES");
            
            tx.success();
        }
        
        System.out.println("Done successfully");
    }
}
