package com.sauzny.dbutils.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Orders extends BaseEntity{

    private int id;
    private int status;
    private int type;
    private int productId;
    private int personId;
    private LocalDateTime createTime;
    private double costPrice;
    private int costScore;
    
    @Override
    public String toString() {
        return id + "\t" + status + "\t" + type + "\t" + productId + "\t" + personId + "\t"
                + createTime.toString().replace("T", " ") + "\t" + costPrice + "\t" + costScore;
    }
    
}
