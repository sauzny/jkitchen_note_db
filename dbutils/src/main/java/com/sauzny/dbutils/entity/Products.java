package com.sauzny.dbutils.entity;

import lombok.Data;

@Data
public class Products extends BaseEntity{

    private int id;
    private String name;
    private int status;
    private double nowPrice;
    private double oldPrice;
    
    @Override
    public String toString() {
        return id + "\t" + name + "\t" + status + "\t" + nowPrice + "\t" + oldPrice;
    }
    
    
}
