package com.example.qtend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.usage.NetworkStats;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2<CircleImageView, SocialAutoCompleteTextView, TagAdapter, StorageReference> extends AppCompatActivity {

    private ImageView close;
    private CircleImageView imageProfile;
    private EditText namea;
    private EditText desiga;
    private Button updatea;
    private Button deletea;
    private Button logout;
    private SocialAutoCompleteTextView search_bar;
    private Object CropImage;
    private Object LayoutInflater;
    private Object ViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        imageProfile = (ImageView) findViewById(R.id.image);
        namea = findViewById(R.id.namea);
        desiga = findViewById(R.id.desiga);
        updatea = findViewById(R.id.updatea);
        deletea = findViewById(R.id.deletea);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity2.this, login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    private static final String TAG = null;

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                        }
                    }
                });

        NetworkStats.Bucket fUser;
        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).addValueEventListener(new ValueEventListener() {
            private CircularArray<Object> Picasso;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                namea.setText(user.getName());
                desiga.setText(user.getDesig());
                Picasso.get().load(user.getImageurl()).into(imageProfile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageProfile.setOnClickListener(new View.OnClickListener() {
            private Object CropImageView;

            @Override
            public void onClick(View v) {
                CropImage.activity().setCropShape(CropImageView.CropShape.OVAL).start(MainActivity2.this);
            }
        });

        updatea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        @Override
        public View onCreateView(LayoutInflater Object inflater;
        inflater, ViewGroup Object container;
        container,
                Bundle savedInstanceState) {

            void view;
            RecyclerView recyclerView;
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            RecyclerView recyclerViewTags;
            recyclerViewTags.setHasFixedSize(true);
            recyclerViewTags.setLayoutManager(new LinearLayoutManager(getContext()));

            ArrayList<> mHashTags = new ArrayList<>();
            ArrayList<> mHashTagsCount = new ArrayList<>();
            TagAdapter tagAdapter = new TagAdapter(recyclerViewTags.getContext(), mHashTags, mHashTagsCount);
            recyclerViewTags.setAdapter(tagAdapter);

            ArrayList<Object> mUsers = new ArrayList<>();
            UserAdapter userAdapter = new UserAdapter(recyclerViewTags.getContext(), mUsers, true);
            recyclerView.setAdapter(userAdapter);

            search_bar = view.findViewById(R.id.search_bar);

            readUsers();

            search_bar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    searchUser(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString());
                }

                private void filter(String toString) {
                }
            });

            return view;
        }

    }

    private Context getContext() {
    }

    private void updateProfile() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", namea.getText().toString());
        map.put("designation", desiga.getText().toString());

        NetworkStats.Bucket fUser;
        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).updateChildren(map);
    }

    private void uploadImage() {

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

                        NetworkStats.Bucket fUser;
                        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).child("imageurl").setValue(url);
                    } else {
                        Toast.makeText(MainActivity2.this, "Upload failed!", Toast.LENGTH_SHORT).show();
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



    private void readUsers() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (TextUtils.isEmpty(search_bar.getText().toString())){
                    ArraySet<Object> mUsers;
                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        User user = snapshot.getValue(User.class);
                        mUsers.add(user);
                    }

                    ArrayAdapter<Object> userAdapter;
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void searchUser (String s) {
        Query query = FirebaseDatabase.getInstance().getReference().child("Users")
                .orderByChild("username").startAt(s).endAt(s + "\uf8ff");

        ((Query) query).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArraySet<Object> mUsers;
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    mUsers.add(user);
                }
                ArrayAdapter<Object> userAdapter;
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}