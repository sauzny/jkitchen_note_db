package com.sauzny.neo4j.w3cschool;

import org.neo4j.graphdb.RelationshipType;

// 6. 要创建关系，我们需要关系类型。 通过实现Neo4j“关系类型”创建枚举。
public enum TutorialRelationships implements RelationshipType{
    JVM_LANGIAGES,NON_JVM_LANGIAGES;
}
