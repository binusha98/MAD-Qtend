package com.example.inquiry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DeleteInquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_inquiry);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ioptions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.textView15){

        }

        if (id == R.id.textView22){
            Intent updateIntent = new Intent(DeleteInquiry.this,SearchInquiries.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView){
            Intent updateIntent = new Intent(DeleteInquiry.this,AddInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView8){
            Intent updateIntent = new Intent(DeleteInquiry.this,EditInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView23){
            Intent updateIntent = new Intent(DeleteInquiry.this,MyInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView43){
            Intent updateIntent = new Intent(DeleteInquiry.this,HowAboutOurService.class);
            startActivity(updateIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}