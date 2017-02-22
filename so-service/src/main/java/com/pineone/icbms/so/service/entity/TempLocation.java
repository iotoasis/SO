package com.pineone.icbms.so.service.entity;

public class TempLocation {
    String con;

    public TempLocation() {
    }

    public TempLocation(String con) {
        this.con = con;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    @Override
    public String toString() {
        return "TempLocation{" +
                "con='" + con + '\'' +
                '}';
    }
}
