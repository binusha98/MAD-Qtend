package com.example.inquiry;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Ratingservice implements Serializable {
    @Exclude
    private String Emp_ID;
    private String Emp_name;
    private String Emp_email;
    private String Rating;
    public Ratingservice(){}
    public Ratingservice(String Emp_ID, String Emp_name, String Emp_email) {
        this.Emp_ID = Emp_ID;
        this.Emp_name = Emp_name;
        this.Emp_email = Emp_email;
        this.Rating = Rating;
    }

    public String getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(String emp_ID) {
        Emp_ID = emp_ID;
    }

    public String getEmp_name() {
        return Emp_name;
    }

    public void setEmp_name(String emp_name) {
        Emp_name = emp_name;
    }

    public String getEmp_email() {
        return Emp_email;
    }

    public void setEmp_email(String emp_email) {
        Emp_email = emp_email;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }



}
