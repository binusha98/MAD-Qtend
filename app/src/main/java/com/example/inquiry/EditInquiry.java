package com.example.inquiry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class EditInquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inquiry);
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
        if (id == R.id.textView8){

        }

        if (id == R.id.textView){
            Intent updateIntent = new Intent(EditInquiry.this,AddInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView23){
            Intent updateIntent = new Intent(EditInquiry.this,MyInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView43){
            Intent updateIntent = new Intent(EditInquiry.this,HowAboutOurService.class);
            startActivity(updateIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}