package com.example.inquiry;

//import android.annotation.SuppressLint;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.HashMap;

public class AddInquiry extends AppCompatActivity {

    DatePickerDialog picker;
    EditText eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inquiry);


        final EditText add_id = findViewById(R.id.add_id);
        final EditText add_name = findViewById(R.id.add_name);
        final EditText add_email = findViewById(R.id.add_email);
        final EditText add_date = findViewById(R.id.add_date);
        final EditText add_inquiry_type = findViewById(R.id.add_inquiry_type);
        final EditText add_description = findViewById(R.id.add_description);

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
            add_id.setText(inqn_edit.getEmployee_ID());
            add_name.setText(inqn_edit.getEmployee_Name());
            add_email.setText(inqn_edit.getEmployee_Email());
            add_date.setText(inqn_edit.getEmployee_IDate());
            add_inquiry_type.setText(inqn_edit.getInquiryType());
            add_description.setText(inqn_edit.getIDescription());

            button_openI.setVisibility(View.GONE);
        }
        else
            {
                button.setText("SUBMIT");
                button_openI.setVisibility(View.VISIBLE);
            }

        button.setOnClickListener(v->
        {
///////////////////check fill the all fields
            if(add_id.getText().toString().length()>0 ||
                    add_name.getText().toString().length()>0 ||
                    add_email.getText().toString().length()>0 ||
                    add_date.getText().toString().length()>0 ||
                    add_inquiry_type.getText().toString().length()>0 ||
                    add_description.getText().toString().length()>0) {

                Inquiryn inqn = new Inquiryn(
                        add_id.getText().toString(),
                        add_name.getText().toString(),
                        add_email.getText().toString(),
                        add_date.getText().toString(),
                        add_inquiry_type.getText().toString(),
                        add_description.getText().toString());
////////////////Insert data
                if (inqn_edit == null) {

                    dao.add(inqn).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(this, "Your Inquiry is added", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                    //////////////////////////////Update data
                } else {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("Employee_ID", add_id.getText().toString());
                    hashMap.put("Employee_Name", add_name.getText().toString());
                    hashMap.put("Employee_Email", add_email.getText().toString());
                    hashMap.put("Employee_IDate", add_date.getText().toString());
                    hashMap.put("InquiryType", add_inquiry_type.getText().toString());
                    hashMap.put("IDescription", add_description.getText().toString());

                    dao.update(inqn_edit.getKey(), hashMap).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
///////////////////check fill the all fields
            }else{
                //If there are any empty fields, this messages displayed
                Toast.makeText(this, "There can not be empty fields!! Please fill in the blanks", Toast.LENGTH_SHORT).show();
            }


        });
        //calender
        eText=(EditText) findViewById(R.id.add_date);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddInquiry.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }
    //@SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    //public void SelectInquiryType(View view)
    //{
       // boolean checked = ((RadioButton) view).isChecked();
    // switch (view.getId())
    //  {
    //    case R.id.radioButton:
    //         if (checked) {
    //             final_result.setText("You selected Confirmation Inquiry");
    //             final_result.setEnabled(true);
    //         }
    //         else
    //         {
    //            final_result.setEnabled(false);
    //        }
    //         break;
    //     case R.id.radioButton2:
    //           if (checked){
    //               final_result.setText("You selected Guided Inquiry");
    //               final_result.setEnabled(true);
    //           }
    //           else
    //           {
    //               final_result.setEnabled(false);
    //           }
    //        break;
    //   case R.id.radioButton3:
    //    if (checked){
        //      final_result.setText("You selected Other Inquiry");
        //      final_result.setEnabled(true);
        //   }
    //   else
    //   {
                    //       final_result.setEnabled(false);
                    //    }
    //       break;
    //  }
    // }
//////////////////////////////////////////menu
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