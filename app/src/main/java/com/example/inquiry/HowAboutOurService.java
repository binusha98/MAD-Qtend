package com.example.inquiry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HowAboutOurService extends AppCompatActivity {


    RatingBar ratingBar;
    Button btSubmit;
    // EditText filed for Email
    EditText etMail;

    // Button to validate the Email address
    //Button bValidate;
    private void emailValidator(EditText etMail) {
        String emailToText = etMail.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }
    }



    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_about_our_service);
        final EditText addTextTextPersonName13 = findViewById(R.id.addTextTextPersonName13);
        final EditText addTextTextPersonName14 = findViewById(R.id.addTextTextPersonName14);
        final EditText addTextTextEmailAddress7 = findViewById(R.id.addTextTextEmailAddress7);
       // RatingBar rating_bar = findViewById(R.id.rating_bar);
        Button button4 = findViewById(R.id.button4);

        etMail = findViewById(R.id.add_email);
        btSubmit = findViewById(R.id.button4);

        DAOservice dao = new DAOservice();

        button4.setOnClickListener(view ->
        {

            if(addTextTextPersonName13.getText().toString().length()>0 ||
                    addTextTextPersonName14.getText().toString().length()>0 ||
                    addTextTextEmailAddress7.getText().toString().length()>0) {

            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailValidator(etMail);
                }


            });

            //float rating = ratingBar.getRating();
            Ratingservice rat =new Ratingservice(
                    addTextTextPersonName13.getText().toString(),
                    addTextTextPersonName14.getText().toString(),
                    addTextTextEmailAddress7.getText().toString());
            dao.add(rat).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "send your rating,thank you!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

            }else{
                //If there are any empty fields, this messages displayed
                Toast.makeText(this, "There can not be empty fields!! Please fill in the blanks", Toast.LENGTH_SHORT).show();
            }
        });


        ratingBar = findViewById(R.id.rating_bar);
        btSubmit = findViewById(R.id.button4);

        //btSubmit.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
               // String s = String.valueOf(ratingBar.getRating());
               // Toast.makeText(getApplicationContext(), s+"Star, Thank you"
               // ,Toast.LENGTH_SHORT).show();
           // }
       // });
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