package com.example.employeesalary;

        import com.google.firebase.database.Exclude;

        import java.io.Serializable;

public class Employee_Salary implements Serializable{

    @Exclude
    private String key;
    private String name;
    private String designation;
    private String basic_salary;
    private String deductions;
    private String bonus;
    private String total_salary;
    public Employee_Salary(){}


    public Employee_Salary(String name, String designation, String basic_salary, String deductions, String bonus, String total_salary) {
        this.name = name;
        this.designation = designation;
        this.basic_salary = basic_salary;
        this.deductions = deductions;
        this.bonus = bonus;
        this.total_salary = total_salary;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBasic_salary() {
        return basic_salary;
    }

    public void setBasic_salary(String basic_salary) {
        this.basic_salary = basic_salary;
    }

    public String getDeductions() {
        return deductions;
    }

    public void setDeductions(String deductions) {
        this.deductions = deductions;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }


    public String getTotal_salary() {
        return total_salary;
    }

    public void setTotal_salary(String total_salary) {
        this.total_salary = total_salary;
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

