package com.example.mad_app;

//import Statements
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class attendance extends AppCompatActivity {
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //referencing layout file
        setContentView(R.layout.activity_attendance);
        final EditText add_emp_ID = findViewById(R.id.add_emp_ID);
        final EditText add_emp_NAME = findViewById(R.id.add_emp_NAME);
        final EditText add_emp_OTHRS = findViewById(R.id.add_emp_OTHRS);
        final EditText add_emp_WORKDAYS = findViewById(R.id.add_emp_WORKDAYS);
        final EditText add_emp_NOPAYDAYS = findViewById(R.id.add_emp_NOPAYDAYS);


        //button linking
        Button btn = findViewById(R.id.btn_submit);
        Button btn_open = findViewById(R.id.btn_open);

        btn_open.setOnClickListener(v->
        {
            Intent intent =new Intent(attendance.this, RVActivity.class);
            startActivity(intent);
        });

        //making database access object
        DAOEmployee_Attendance dao = new DAOEmployee_Attendance();

        //Check whether update or add data
        Employee_Attendance emp_edit = (Employee_Attendance)getIntent().getSerializableExtra("EDIT");
        if(emp_edit !=null)
        {
            btn.setText("UPDATE");
            add_emp_ID.setText(emp_edit.getId());
            add_emp_NAME.setText(emp_edit.getName());
            add_emp_OTHRS.setText(emp_edit.getOT_hrs());
            add_emp_WORKDAYS.setText(emp_edit.getWork_days());
            add_emp_NOPAYDAYS.setText(emp_edit.getNo_pay_days());
            btn_open.setVisibility(View.GONE);
        }
        else
        {
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(v->
        {
            //Check out is there any empty fields, before adding
            if(add_emp_ID.getText().toString().length()>0 ||
                    add_emp_NAME.getText().toString().length()>0 ||
                    add_emp_OTHRS.getText().toString().length()>0 ||
                    add_emp_WORKDAYS.getText().toString().length()>0 ||
                    add_emp_NOPAYDAYS.getText().toString().length()>0){

                //Add data
                Employee_Attendance atd = new Employee_Attendance(
                        add_emp_ID.getText().toString(),
                        add_emp_NAME.getText().toString(),
                        add_emp_OTHRS.getText().toString(),
                        add_emp_WORKDAYS.getText().toString(),
                        add_emp_NOPAYDAYS.getText().toString());

                if (emp_edit == null) {
                    //Inserting  data
                    dao.add(atd).addOnSuccessListener(suc ->
                    {   //Success Message
                        Toast.makeText(this, "Record is Inserted", Toast.LENGTH_SHORT).show();

                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    // retrieve data from database and update
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("id", add_emp_ID.getText().toString());
                    hashMap.put("name", add_emp_NAME.getText().toString());
                    hashMap.put("ot_hrs", add_emp_OTHRS.getText().toString());
                    hashMap.put("work_days", add_emp_WORKDAYS.getText().toString());
                    hashMap.put("no_pay_days", add_emp_NOPAYDAYS.getText().toString());
                    dao.update(emp_edit.getKey(), hashMap).addOnSuccessListener(suc ->
                    {    //Success Message
                        Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
            else{
                //If there are any empty fields, this messages displayed
                Toast.makeText(this, "There can not be empty fields", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Please Fill out Empty Fields", Toast.LENGTH_SHORT).show();
            }


        });

    }
    //menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_ish_emp, menu);
        return super.onCreateOptionsMenu(menu);

    }
    //menu item access
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile){

        }
        if (id == R.id.action_home){
            Intent updateIntent = new Intent(attendance.this,attendance.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_update){
            Intent updateIntent = new Intent(attendance.this,Update_Attendance.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_delete){
            Intent deleteIntent = new Intent(attendance.this,Delete_Attendance.class);
            startActivity(deleteIntent);
        }
        if (id == R.id.action_mark){
            Intent markIntent = new Intent(attendance.this,Mark_Attendance.class);
            startActivity(markIntent);
        }
        if (id == R.id.s1){
            Intent searchIntent = new Intent(attendance.this,Search.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }


}