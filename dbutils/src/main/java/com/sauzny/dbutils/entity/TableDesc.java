package com.sauzny.dbutils.entity;

import lombok.Data;

@Data
public class TableDesc {

    private String field;
    
    private String type;

    public TableDesc(String field, String type) {
        super();
        this.field = field;
        this.type = type;
    }

    public TableDesc() {
        super();
    }
    
}
