package com.sauzny.dbutils.entity.tools;

import java.util.Map;

import com.google.common.collect.Maps;

public class ToolsConstant {
    
    public static Map<String, String> j2dbTypeMapping = Maps.newHashMap();
    
    static{
        j2dbTypeMapping.put("long","long");
        j2dbTypeMapping.put("double","double");
        j2dbTypeMapping.put("int","int(11)");
        j2dbTypeMapping.put("boolean","varchar(255)");
        j2dbTypeMapping.put("java.time.LocalDate","date");
        j2dbTypeMapping.put("java.time.LocalDateTime","datetime");
        j2dbTypeMapping.put("java.sql.Date","date");
        j2dbTypeMapping.put("java.lang.String","varchar(255)");
    }

    public static Map<String, String> db2jTypeMapping = Maps.newHashMap();
    
    static{
        db2jTypeMapping.put("int","java.lang.Integer");
        db2jTypeMapping.put("double","java.lang.Double");
        db2jTypeMapping.put("date","java.time.LocalDate");
        db2jTypeMapping.put("datetime","java.time.LocalDateTime");
        db2jTypeMapping.put("varchar","java.lang.String");
        db2jTypeMapping.put("bigint","java.lang.Long");
    }
}
