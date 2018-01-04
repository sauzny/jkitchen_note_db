package com.sauzny.redisson.entity;

public class Student {

    public int id;

    public String value1;
    
    public String value2;
    
    public Student() {
        super();
    }

    public Student(int id) {
        super();
        this.id = id;
    }

    public Student(String value1, String value2) {
        super();
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", value1=" + value1 + ", value2=" + value2 + "]";
    }
    
}
