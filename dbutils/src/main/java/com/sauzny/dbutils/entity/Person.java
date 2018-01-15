package com.sauzny.dbutils.entity;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
public class Person extends BaseEntity{

    private int id;
    private String name;
    private int age;
    private String gender;
    private String hometown;
    private String phone;
    private String email;
    private LocalDate birthday;
    // 余额
    private double balance;
    @Override
    public String toString() {
        return id + "\t" + name + "\t" + age + "\t" + gender + "\t" + hometown + "\t" + phone
                + "\t" + email + "\t" + birthday + "\t" + balance;
    }
    
    
}
