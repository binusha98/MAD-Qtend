package com.example.inquiry;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Inquiryn implements Serializable
{
    @Exclude
    private String key;
    private String Employee_ID;
    private String Employee_Name;
    private String Employee_Email;
    private String Employee_IDate;
    private String InquiryType;
    private String IDescription;



    public Inquiryn(){}

    public Inquiryn(String Employee_ID, String Employee_Name, String Employee_Email, String Employee_IDate,String InquiryType, String IDescription) {
        this.Employee_ID = Employee_ID;
        this.Employee_Name = Employee_Name;
        this.Employee_Email = Employee_Email;
        this.Employee_IDate = Employee_IDate;
        this.InquiryType = InquiryType;
        this.IDescription = IDescription;


    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        Employee_ID = employee_ID;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        Employee_Name = employee_Name;
    }

    public String getEmployee_Email() {
        return Employee_Email;
    }

    public void setEmployee_Email(String employee_Email) {
        Employee_Email = employee_Email;
    }

    public String getEmployee_IDate() {
        return Employee_IDate;
    }

    public void setEmployee_IDate(String employee_IDate) {
        Employee_IDate = employee_IDate;
    }

    public String getIDescription() {
        return IDescription;
    }

    public void setIDescription(String IDescription) {
        this.IDescription = IDescription;
    }

    public String getInquiryType() {
        return InquiryType;
    }

    public void setInquiryType(String inquiryType) {
        InquiryType = inquiryType;
    }


}
