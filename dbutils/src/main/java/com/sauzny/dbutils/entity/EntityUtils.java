package com.sauzny.dbutils.entity;

import java.lang.reflect.Method;

public final class EntityUtils {

    private EntityUtils(){}
    
    public static void printAllSetMethods(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        
        for(Method method : methods){
            if(method.getName().contains("set")){
                System.out.println(method.getName());
            }
        }
    }
}
