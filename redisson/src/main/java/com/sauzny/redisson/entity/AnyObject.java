package com.sauzny.redisson.entity;

public class AnyObject {

    public int id;

    public AnyObject(int id) {
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "AnyObject [id=" + id + "]";
    }
    
}
