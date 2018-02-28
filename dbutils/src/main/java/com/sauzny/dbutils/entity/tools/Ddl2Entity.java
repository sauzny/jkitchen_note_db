package com.sauzny.dbutils.entity.tools;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.sauzny.dbutils.entity.TableDesc;

public class Ddl2Entity {

    public static void then(List<TableDesc> tableDescList){
        
        tableDescList.forEach(tableDesc -> {
            String field = tableDesc.getField();
            String type = tableDesc.getType();
            
            List<String> fieldBlocks = Arrays.asList(field.split("_"));
            
            StringBuilder fieldSb = new StringBuilder(fieldBlocks.get(0));
            
            for(int i=1; i<fieldBlocks.size(); i++){
                String fieldBlock = fieldBlocks.get(i);
                char block1 = Character.toUpperCase(fieldBlock.charAt(0));
                String block2 = fieldBlock.substring(1);

                fieldSb.append(block1 + block2);
            }
            
            String javaType = ToolsConstant.db2jTypeMapping.get(type.split("\\(")[0]);
            
            System.out.println("private " + javaType.split("\\.")[2] + " " + fieldSb + ";");
            
        });
    }
    
    public static void main(String[] args) {
        
        List<TableDesc> tableDescList = Lists.newArrayList();
        tableDescList.add(new TableDesc("id", "bigint(20) unsigned"));
        tableDescList.add(new TableDesc("commission_id", "varchar(64)"));
        tableDescList.add(new TableDesc("earning_time", "datetime"));
        
        Ddl2Entity.then(tableDescList);
    }
}

