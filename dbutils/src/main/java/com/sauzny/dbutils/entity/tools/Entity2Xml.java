package com.sauzny.dbutils.entity.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class Entity2Xml {

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
                String javaType = ToolsConstant.db2jTypeMapping.get(dbType);
                
                System.out.println("<column name=\""+name+"\" uniqueName=\"\" dbType=\""+dbType+"\" javaType=\""+javaType+"\" isShow=\"true\"/>");
            });
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
