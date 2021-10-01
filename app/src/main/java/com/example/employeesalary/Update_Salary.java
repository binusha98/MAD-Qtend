package com.example.employeesalary;

        import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Update_Salary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_salary);
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
            Intent updateIntent = new Intent(Update_Salary.this,Update_Salary.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_update){
            Intent updateIntent = new Intent(Update_Salary.this,Delete_Salary.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_delete){
            Intent deleteIntent = new Intent(Update_Salary.this,Delete_Salary.class);
            startActivity(deleteIntent);
        }
        if (id == R.id.action_add){
            Intent markIntent = new Intent(Update_Salary.this,Add_Salary.class);
            startActivity(markIntent);
        }
        if (id == R.id.app_bar_search){
            Intent searchIntent = new Intent(Update_Salary.this,Search.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}