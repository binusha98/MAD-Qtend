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

import java.util.HashMap;

public class AddInquiry extends AppCompatActivity {
    TextView final_result;
    Inquiryn inquiryn;
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
        Button button_openI = findViewById(R.id.button_openI);
        button_openI.setOnClickListener(v->
        {
            Intent intent = new Intent(AddInquiry.this,SearchInquiries.class);
            startActivity(intent);
        });

        DAOInquiryn dao = new DAOInquiryn();
        Inquiryn inqn_edit = (Inquiryn)getIntent().getSerializableExtra("EDIT");
        if (inqn_edit !=null){
            button.setText("UPDATE");
            editTextTextPersonName.setText(inqn_edit.getEmployee_ID());
            editTextTextPersonName2.setText(inqn_edit.getEmployee_Name());
            editTextTextEmailAddress.setText(inqn_edit.getEmployee_Email());
            editTextDate.setText(inqn_edit.getEmployee_IDate());
            editTextTextMultiLine.setText(inqn_edit.getIDescription());
            radioButton.setText(inqn_edit.getConfirmationInquiry());
            radioButton2.setText(inqn_edit.getGuidedInquiry());
            radioButton3.setText(inqn_edit.getOtherInquiry());
            button_openI.setVisibility(View.GONE);
        }
        else
            {
                button.setText("SUBMIT");
                button_openI.setVisibility(View.VISIBLE);
            }

        button.setOnClickListener(v->
        {

            Inquiryn inqn = new Inquiryn(editTextTextPersonName.getText().toString(),editTextTextPersonName2.getText().toString(),editTextTextEmailAddress.getText().toString(),editTextDate.getText().toString(),editTextTextMultiLine.getText().toString(),radioButton.getText().toString(),radioButton2.getText().toString(),radioButton3.getText().toString());

            if(inqn_edit==null)
            {

                dao.add(inqn).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Your Inquiry is added", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else
            {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Employee_ID", editTextTextPersonName.getText().toString());
                hashMap.put("Employee_Name", editTextTextPersonName2.getText().toString());
                hashMap.put("Employee_Email", editTextTextEmailAddress.getText().toString());
                hashMap.put("Employee_IDate", editTextDate.getText().toString());
                hashMap.put("IDescription", editTextTextMultiLine.getText().toString());
                hashMap.put("ConfirmationInquiry", radioButton.getText().toString());
                hashMap.put("GuidedInquiry", radioButton2.getText().toString());
                hashMap.put("OtherInquiry", radioButton3.getText().toString());
                dao.update(inqn_edit.getKey(), hashMap).addOnSuccessListener(suc ->
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