package com.example.inquiry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class AddInquiry extends AppCompatActivity {
    TextView final_result;

   // Button b;
    Inquiryn inquiryn;
  //  RadioButton radioButton,radioButton2,radioButton3;
  //  FirebaseDatabase database;
  //  DatabaseReference reference;
  //  int i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inquiry);
        final_result = (TextView)findViewById(R.id.result_text);
        final_result.setEnabled(false);

        final EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        final EditText editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        final EditText editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        final EditText editTextDate = findViewById(R.id.editTextDate);

        inquiryn = new Inquiryn();
        RadioButton radioButton = findViewById(R.id.radioButton);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);

        final EditText editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);

        Button button = findViewById(R.id.button);

        ///reference = database.getInstance().getReference().child("User");

        DAOInquiryn dao = new DAOInquiryn();
        getIntent().getSerializableExtra("EDIT");

        button.setOnClickListener(v->
        {

            Inquiryn inqn = new Inquiryn(editTextTextPersonName.getText().toString(),editTextTextPersonName2.getText().toString(),editTextTextEmailAddress.getText().toString(),editTextDate.getText().toString(),editTextTextMultiLine.getText().toString(),radioButton.getText().toString(),radioButton2.getText().toString(),radioButton3.getText().toString());
            ///String n1 = radioButton.getText().toString();
            ///String n2 = radioButton2.getText().toString();
            ///String n3 = radioButton3.getText().toString();

            /// if (radioButton.isChecked()){
            ///inquiryn.setInquiryType(n1);
                //reference.child(String[].valueOf(i+1).setValue(inquiryn);

            ///}else if(radioButton2.isChecked()){
            ///inquiryn.setInquiryType(n2);
                //reference.child(String[].valueOf(i+1).setValue(inquiryn);
            ///}else{
            /// inquiryn.setInquiryType(n3);
            ///  //reference.child(String[].valueOf(i+1).setValue(inquiryn);
            ///}

            dao.add(inqn).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Your Inquiry is added", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        });

        Button button_openI = findViewById(R.id.button_openI);
        button_openI.setOnClickListener(v->
        {
            Intent intent = new Intent(AddInquiry.this,SearchInquiries.class);
            startActivity(intent);
        });
    }
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    public void SelectInquiryType(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.radioButton:
                if (checked) {
                    final_result.setText("You selected Confirmation Inquiry");
                    final_result.setEnabled(true);
                }
                else
                {
                    final_result.setEnabled(false);
                }
                 break;
            case R.id.radioButton2:
                if (checked){
                    final_result.setText("You selected Guided Inquiry");
                    final_result.setEnabled(true);
                }
                else
                {
                    final_result.setEnabled(false);
                }
                break;
            case R.id.radioButton3:
                if (checked){
                    final_result.setText("You selected Other Inquiry");
                    final_result.setEnabled(true);
                }
                else
                {
                    final_result.setEnabled(false);
                }
                break;
        }
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
        if (id == R.id.textView){

        }

        if (id == R.id.textView22){
            Intent updateIntent = new Intent(AddInquiry.this,SearchInquiries.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView8){
            Intent updateIntent = new Intent(AddInquiry.this,EditInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView15){
            Intent updateIntent = new Intent(AddInquiry.this,DeleteInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView23){
            Intent updateIntent = new Intent(AddInquiry.this,MyInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView43){
            Intent updateIntent = new Intent(AddInquiry.this,HowAboutOurService.class);
            startActivity(updateIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}