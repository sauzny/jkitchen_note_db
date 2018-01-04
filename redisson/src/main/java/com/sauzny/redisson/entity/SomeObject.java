package com.sauzny.redisson.entity;

public class SomeObject {

    public int id;

    public String value1;
    
    public String value2;
    
    public SomeObject() {
        super();
    }
    
    public SomeObject(int id) {
        super();
        this.id = id;
    }

    public SomeObject(String value1, String value2) {
        super();
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "SomeObject [id=" + id + ", value1=" + value1 + ", value2=" + value2 + "]";
    }
    
}
