package com.example.mad_app;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Employee_Attendance implements Serializable{

    @Exclude
    //define variables
    private String key;
    private String id;
    private String name;
    private String OT_hrs;
    private String work_days;
    private String no_pay_days;
    public Employee_Attendance(){}

    //constructor
    public Employee_Attendance(String id, String name, String OT_hrs, String work_days, String no_pay_days) {
        this.id = id;
        this.name = name;
        this.OT_hrs = OT_hrs;
        this.work_days = work_days;
        this.no_pay_days = no_pay_days;
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOT_hrs() {
        return OT_hrs;
    }

    public void setOT_hrs(String OT_hrs) {
        this.OT_hrs = OT_hrs;
    }

    public String getWork_days() {
        return work_days;
    }

    public void setWork_days(String work_days) {
        this.work_days = work_days;
    }

    public String getNo_pay_days() {
        return no_pay_days;
    }

    public void setNo_pay_days(String no_pay_days) {
        this.no_pay_days = no_pay_days;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}


