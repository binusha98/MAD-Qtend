package com.example.inquiry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyInquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inquiry);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ieoptions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.textView23){

        }

        if (id == R.id.textView){
            Intent updateIntent = new Intent(MyInquiry.this,AddInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView8){
            Intent updateIntent = new Intent(MyInquiry.this,EditInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView43){
            Intent updateIntent = new Intent(MyInquiry.this,HowAboutOurService.class);
            startActivity(updateIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}