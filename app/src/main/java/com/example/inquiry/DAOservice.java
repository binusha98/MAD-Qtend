package com.example.inquiry;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOservice {
    private DatabaseReference databaseReference;
    public DAOservice()
    {
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Ratingservice.class.getSimpleName());
    }

    public Task<Void> add(Ratingservice rat)
    {
        //if(rat ==null) throw exception
        return databaseReference.push().setValue(rat);
    }
    public Query get()
    {
        return databaseReference;
    }
}
