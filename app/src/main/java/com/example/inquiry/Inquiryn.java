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
    private String ConfirmationInquiry;
    private String GuidedInquiry;
    private String OtherInquiry;
    private String IDescription;

    private String InquiryType;

    public Inquiryn(){}

    public Inquiryn(String employee_ID, String employee_Name, String employee_Email, String employee_IDate, String iDescription,String confirmationInquiry,String guidedInquiry,String otherInquiry) {
        Employee_ID = employee_ID;
        Employee_Name = employee_Name;
        Employee_Email = employee_Email;
        Employee_IDate = employee_IDate;
        IDescription = iDescription;
        ConfirmationInquiry = confirmationInquiry;
        GuidedInquiry = guidedInquiry;
        OtherInquiry = otherInquiry;

    }





    public String getConfirmationInquiry() {
     return ConfirmationInquiry;
     }

     public void setConfirmationInquiry(String confirmationInquiry) {
            ConfirmationInquiry = confirmationInquiry;
     }

    public String getGuidedInquiry() {
      return GuidedInquiry;
     }

    public void setGuidedInquiry(String guidedInquiry) {
       GuidedInquiry = guidedInquiry;
    }

    public String getOtherInquiry() {
       return OtherInquiry;
    }

    public void setOtherInquiry(String otherInquiry) {
          OtherInquiry = otherInquiry;
        }

    public String getInquiryType() {
     return InquiryType;
     }

     public void setInquiryType(String inquiryType) {
       InquiryType = inquiryType;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }





}
