package com.sauzny.dbutils.entity;

public class BaseEntity implements CharSequence{

    @Override
    public int length() {
        // TODO Auto-generated method stub
        return this.toString().length();
    }

    @Override
    public char charAt(int index) {
        // TODO Auto-generated method stub
        return this.toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        // TODO Auto-generated method stub
        return this.toString().subSequence(start, end);
    }

}
