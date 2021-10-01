
package com.example.mad_app;

        import com.google.firebase.database.Exclude;

        import java.io.Serializable;

public class Employee_M_Ateendance implements Serializable {

    @Exclude
    //define variables
    private String key1;
    private String idA;
    private String date;

    public Employee_M_Ateendance(){}

    //constructor
    public Employee_M_Ateendance(String idA, String date) {
        this.idA = idA;
        this.date = date;

    }
    //getters and setters
    public String getidA() {
        return idA;
    }

    public void setidA(String idA) {
        this.idA = idA;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getKey1()
    {
        return key1;
    }

    public void setKey1(String key1)
    {
        this.key1 = key1;
    }
}


