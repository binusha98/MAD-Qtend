package com.example.qtend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class register extends AppCompatActivity {


    private EditText unamee;
    private EditText phne;
    private EditText id;
    private EditText name;
    private EditText desig;
    private EditText email;
    private EditText pswde;
    private Button signupe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        id = findViewById(R.id.id);
        unamee = findViewById(R.id.unamee);
        name = findViewById(R.id.name);
        desig = findViewById(R.id.desig);
        email = findViewById(R.id.email);
        phne = findViewById(R.id.phne);
        pswde = findViewById(R.id.pswde);
        signupe = findViewById(R.id.signupe);

        signupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtId = id.getText().toString();
                String txtName = name.getText().toString();
                String txtDesignation = desig.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPhone = phne.getText().toString();
                String txtUsername = unamee.getText().toString();
                String txtPassword = pswde.getText().toString();

                if (TextUtils.isEmpty(txtId) || TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtDesignation)
                        || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtUsername)
                         || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(register.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 8){
                    Toast.makeText(register.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtId, txtName, txtDesignation, txtEmail, txtPhone, txtUsername , txtPassword);
                }
            }
        });

    }

    private void registerUser(final String id, final String name, final String desig, final String email, final String phne, final String unamee, String pswde) {

        pd.setMessage("Please Wait!");
        pd.show();

        mAuth.createUserWithUsernameAndPassword(unamee , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String , Object> map = new HashMap<>();
                map.put("phone" , phne);
                map.put("designation" , desig);
                map.put("name" , name);
                map.put("email", email);
                map.put("username" , unamee);
                map.put("id" , mAuth.getCurrentUser().getUid());
                map.put("imageurl" , "default");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}