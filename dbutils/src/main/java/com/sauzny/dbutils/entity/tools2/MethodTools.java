package com.sauzny.dbutils.entity.tools2;

import java.lang.reflect.Method;

import com.sauzny.dbutils.entity.Person;

public class MethodTools {

    public static void setget(Class<?> clazz, String otherName){
        String simpleName = clazz.getSimpleName();
        String className = simpleName.substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            if(method.getName().startsWith("set")){
                System.out.println(className+"."+method.getName()+"("+className+".g"+method.getName().substring(1)+"());");
            }
        }
    }
    
    public static void main(String[] args) {
        MethodTools.setget(Person.class, "person");
    }
}
