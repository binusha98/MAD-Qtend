package com.example.inquiry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class HowAboutOurService extends AppCompatActivity {

    RatingBar ratingBar;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_about_our_service);

        ratingBar = findViewById(R.id.rating_bar);
        btSubmit = findViewById(R.id.button4);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), s+"Star, Thank you"
                ,Toast.LENGTH_SHORT).show();
            }
        });
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
        if (id == R.id.textView43){

        }

        if (id == R.id.textView){
            Intent updateIntent = new Intent(HowAboutOurService.this,AddInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView23){
            Intent updateIntent = new Intent(HowAboutOurService.this,MyInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView8){
            Intent updateIntent = new Intent(HowAboutOurService.this,EditInquiry.class);
            startActivity(updateIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}