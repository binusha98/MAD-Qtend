package com.example.qtend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;

import android.app.MediaRouteButton;
import android.app.usage.NetworkStats;
import android.content.Context;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.HashMap;

public class MainActivity<CircleImageView, StorageReference> extends AppCompatActivity {

    private CircleImageView image;
    private EditText eemail;
    private EditText ephone;
    private Button update;
    private Button logouta;
    private Button changePhoto;
    private TextView empId;
    private TextView empname;
    private TextView empdesig;
    private Object CropImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findCircleImageViewById(R.id.image);
        eemail = findViewById(R.id.eemail);
        ephone = findViewById(R.id.ephone);
        update = findViewById(R.id.update);
        logouta = findViewById(R.id.logouta);
        changePhoto = findViewById(R.id.changePhoto);
        empId = findViewById(R.id.empId);
        empname = findViewById(R.id.empname);
        empdesig = findViewById(R.id.empdesig);;

        NetworkStats.Bucket fUser;
        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).addValueEventListener(new ValueEventListener() {
            private CircularArray<Object> Picasso;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                eemail.setText(user.getEmail());
                ephone.setText(user.getPhne());
                Picasso.get().load(user.getImageurl()).into(image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logouta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });

        changePhoto.setOnClickListener(new View.OnClickListener() {
            private Object CropImageView;
            private Object CropImage;

            @Override
            public void onClick(View v) {
                CropImage.activity().setCropShape(CropImageView.CropShape.OVAL).start(MainActivity.this);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            private Object CropImageView;

            @Override
            public void onClick(View v) {
                CropImage.activity().setCropShape(CropImageView.CropShape.OVAL).start(MainActivity.this);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

    }

    private CircleImageView findCircleImageViewById(int image) {
    }

    private void updateProfile() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", eemail.getText().toString());
        map.put("phone", ephone.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).updateChildren(map);
    }

    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        Object mImageUri;
        if (mImageUri != null) {
            DataSnapshot storageRef;
            final StorageReference fileRef = storageRef.child(System.currentTimeMillis() + ".jpeg");

            uploadTask = fileRef.putFile(mImageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return  fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String url = downloadUri.toString();

                        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).child("imageurl").setValue(url);
                        pd.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Object mImageUri = result.getUri();

            uploadImage();
        } else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

        String data = getContext().getSharedPreferences("PROFILE", Context.MODE_PRIVATE).getString("profileId", "none");

        String profileId;
        if (data.equals("none")) {
            profileId = fUser.getUid();
        } else {
            profileId = data;
            getContext().getSharedPreferences("PROFILE", Context.MODE_PRIVATE).edit().clear().apply();
        }

        View view;
        image = view.findViewById(R.id.image);
        empId = view.findViewById(R.id.empId);
        empname = view.findViewById(R.id.empname);
        empdesig = view.findViewById(R.id.empdesig);

        userInfo();

        if (profileId.equals(fUser.getUid())) {
            update.setText("Edit profile");
        } else {
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = update.getText().toString();

                if (btnText.equals("Edit profile")) {
                    startActivity(new Intent(getContext(), MainActivity.class));
                } else {
                    String profileId;
                    NetworkStats.Bucket fUser;
                    if (btnText.equals("follow")) {
                        FirebaseDatabase.getInstance().getReference().child("Follow").child(fUser.getUid())
                                .child("following").child(profileId).setValue(true);

                        FirebaseDatabase.getInstance().getReference().child("Follow").child(profileId)
                                .child("followers").child(fUser.getUid()).setValue(true);
                    } else {
                        FirebaseDatabase.getInstance().getReference().child("Follow").child(fUser.getUid())
                                .child("following").child(profileId).removeValue();

                        FirebaseDatabase.getInstance().getReference().child("Follow").child(profileId)
                                .child("followers").child(fUser.getUid()).removeValue();
                    }
                }
            }

            private Context getContext() {
            }
        });

        MediaRouteButton recyclerView;
        recyclerView.setVisibility(View.VISIBLE);
        MediaRouteButton recyclerViewSaves;
        recyclerViewSaves.setVisibility(View.GONE);

        return view;
    }

    private Context getContext() {
    }

    private void userInfo() {

        String profileId;
        FirebaseDatabase.getInstance().getReference().child("Users").child(profileId).addValueEventListener(new ValueEventListener() {
            private CircularArray<Object> Picasso;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                Picasso.get().load(user.getImageurl()).into(image);
                empId.setText(user.getId());
                empname.setText(user.getName());
                empdesig.setText(user.getDesig());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}