package com.sauzny.dbutils.entity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class Entity2Xml {

    private static Map<String, String> typeMapping = Maps.newHashMap();
    
    static{
        typeMapping.put("int","java.lang.Integer");
        typeMapping.put("double","java.lang.Double");
        typeMapping.put("date","java.time.LocalDate");
        typeMapping.put("datetime","java.time.LocalDateTime");
        typeMapping.put("varchar","java.lang.String");
    }

    public static void main(String[] args) {
        
        try {
            List<String> list = Files.readAllLines(Paths.get("E:/gbase/gquerydemo/data/all_column.txt"), StandardCharsets.UTF_8);
        
            list.forEach(line ->{
                
                String[] name_type = line.split("\t");
                String name = name_type[0];
                String dbType = name_type[1];
                if(dbType.indexOf("(") > 0){
                    dbType = dbType.split("\\(")[0];
                }
                String javaType = typeMapping.get(dbType);
                
                System.out.println("<column name=\""+name+"\" uniqueName=\"\" dbType=\""+dbType+"\" javaType=\""+javaType+"\" isShow=\"true\"/>");
            });
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
