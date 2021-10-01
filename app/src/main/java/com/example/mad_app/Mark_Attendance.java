package com.example.mad_app;

//import Statements
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.widget.DatePicker;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class Mark_Attendance extends AppCompatActivity implements OnMapReadyCallback {
    //Calender declarations
    DatePickerDialog picker;
    EditText eText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //set related layout file
        setContentView(R.layout.activity_mark_attendance);

        //load the map to the fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync((OnMapReadyCallback) this);
        }


        final EditText empm_id = findViewById(R.id.empm_id);
        final EditText editText1 = findViewById(R.id.add_date);

        //referencing ids of buttons in xml file
        Button btn = findViewById(R.id.button4);
        Button btn_open = findViewById(R.id.btn_view);

        btn_open.setOnClickListener(v->
        {
            Intent intent =new Intent(Mark_Attendance.this, RVActivity1.class);
            startActivity(intent);
        });

        //making database access object
        DAO_Attendance daoa = new DAO_Attendance();
        //Check whether update or add data
        Employee_M_Ateendance atd_edit = (Employee_M_Ateendance) getIntent().getSerializableExtra("EDIT");
        if(atd_edit !=null)
        {
            btn.setText("UPDATE");
            empm_id.setText(atd_edit.getidA());
            editText1.setText(atd_edit.getdate());

            btn_open.setVisibility(View.GONE);
        }
        else
        {
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(v->
        {
            //Check out is there any empty fields, before adding
            if(empm_id.getText().toString().length()>0 ||
                    editText1.getText().toString().length()>0) {
                Employee_M_Ateendance atda = new Employee_M_Ateendance(
                        empm_id.getText().toString(),
                        editText1.getText().toString());

                //Add data
                if (atd_edit == null) {
                    //Inserting  data
                    daoa.add(atda).addOnSuccessListener(suc ->
                    {   //Success Message
                        Toast.makeText(this, "Record is Inserted", Toast.LENGTH_SHORT).show();

                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    // retrieve data from database and update
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("e_id", empm_id.getText().toString());
                    hashMap.put("e_date", editText1.getText().toString());
                    daoa.update(atd_edit.getKey1(), hashMap).addOnSuccessListener(suc ->
                    {    //Success Message
                        Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
            else{
                Toast.makeText(this, "There can not be empty fields", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Please Fill out Empty Fields", Toast.LENGTH_SHORT).show();
            }
        });



        //get the date from data picker and store
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
                picker = new DatePickerDialog(Mark_Attendance.this,
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



    // Get a handle to the GoogleMap object and display marker.
        public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Matara, Sri Lanka,
        // and move the map's camera to the same location.
        LatLng Matara = new LatLng(5.94851,  80.53528);
        googleMap.addMarker(new MarkerOptions()
                .position(Matara)
                .title("Marker in Matara"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Matara));
    }


    //menu items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_ish_emp, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile){

        }
        if (id == R.id.action_home){
            Intent updateIntent = new Intent(Mark_Attendance.this,attendance.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_update){
            Intent updateIntent = new Intent(Mark_Attendance.this,Update_Attendance.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_delete){
            Intent deleteIntent = new Intent(Mark_Attendance.this,Delete_Attendance.class);
            startActivity(deleteIntent);
        }
        if (id == R.id.action_mark){
            Intent markIntent = new Intent(Mark_Attendance.this,Mark_Attendance.class);
            startActivity(markIntent);
        }
        if (id == R.id.app_bar_search){
            Intent searchIntent = new Intent(Mark_Attendance.this,Search.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}