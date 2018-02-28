package com.sauzny.dbutils.entity.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.sauzny.dbutils.entity.Orders;
import com.sauzny.dbutils.entity.Person;
import com.sauzny.dbutils.entity.Products;

public class Entity2DDL {

    public static String entity2DDL(Class<?> clazz){
        
        String result = "CREATE TABLE `"+clazz.getSimpleName().toLowerCase()+"` (";
        
        List<String> fieldList = new ArrayList<String>();
        
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields){
            String name = field.getName();
            String typeName = field.getType().getName();
            if(typeName.contains(".") && !typeName.startsWith("java")){
                continue;
            }
            fieldList.add("`"+name+"` " + ToolsConstant.j2dbTypeMapping.get(typeName));
        }
        
        return result+Joiner.on(",").join(fieldList)+");";
    }
    
    public static void main(String[] args) {
        
        String sql1 = Entity2DDL.entity2DDL(Orders.class);
        String sql2 = Entity2DDL.entity2DDL(Person.class);
        String sql3 = Entity2DDL.entity2DDL(Products.class);
        
        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
    }
}
