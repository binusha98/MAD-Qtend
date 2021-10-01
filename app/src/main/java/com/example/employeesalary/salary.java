
package com.example.employeesalary;

        import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class salary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        final EditText add_name = findViewById(R.id.add_name);
        final EditText add_Designation = findViewById(R.id.add_Designation);
        final EditText add_basicSalary = findViewById(R.id.add_basicSalary);
        final EditText add_deductions = findViewById(R.id.add_deductions);
        final EditText add_bonus = findViewById(R.id.add_bonus);
        final EditText add_totalSalary = findViewById(R.id.add_totalSalary);

        Button btn = findViewById(R.id.btn_submit);


        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v->
        {
            Intent intent =new Intent(salary.this, RVActivity.class);
            startActivity(intent);
        });


        DAOEmployee_Salary dao = new DAOEmployee_Salary();

        Employee_Salary sal_edit = (Employee_Salary)getIntent().getSerializableExtra("EDIT");
        if(sal_edit !=null)
        {
            btn.setText("UPDATE");
            add_name.setText(sal_edit.getName());
            add_Designation.setText(sal_edit.getDesignation());
            add_basicSalary.setText(sal_edit.getBasic_salary());
            add_deductions.setText(sal_edit.getDeductions());
            add_bonus.setText(sal_edit.getBonus());
            add_totalSalary.setText(sal_edit.getTotal_salary());
            btn_open.setVisibility(View.GONE);
        }
        else
        {
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(v->
        {
            Employee_Salary atd = new Employee_Salary(
                    add_name.getText().toString(),
                    add_Designation.getText().toString(),
                    add_basicSalary.getText().toString(),
                    add_deductions.getText().toString(),
                    add_bonus.getText().toString(),
                    add_totalSalary.getText().toString()
            );
            if(sal_edit==null) {
                dao.add(atd).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is Inserted", Toast.LENGTH_SHORT).show();

                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else {

                HashMap<String,Object> hashMap = new HashMap<>();

                hashMap.put("name",add_name.getText().toString());
                hashMap.put("Designation",add_Designation.getText().toString());
                hashMap.put("basicSalary",add_basicSalary.getText().toString());
                hashMap.put("deductions",add_deductions.getText().toString());
                hashMap.put("bonus",add_bonus.getText().toString());
                hashMap.put("totalSalary",add_totalSalary.getText().toString());

                dao.update(sal_edit.getKey(), hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_bin_sal, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile){

        }
        if (id == R.id.action_home){
            Intent updateIntent = new Intent(salary.this,Update_Salary.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_update){
            Intent updateIntent = new Intent(salary.this,Update_Salary.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_delete){
            Intent deleteIntent = new Intent(salary.this,Delete_Salary.class);
            startActivity(deleteIntent);
        }
        if (id == R.id.action_add){
            Intent markIntent = new Intent(salary.this,Add_Salary.class);
            startActivity(markIntent);
        }
        if (id == R.id.s1){
            Intent searchIntent = new Intent(salary.this,Search.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }


}