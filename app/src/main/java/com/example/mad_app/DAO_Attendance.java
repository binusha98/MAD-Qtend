package com.example.mad_app;
//import statements
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;


public class DAO_Attendance {

    private DatabaseReference databaseReference;

    public DAO_Attendance() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Employee_M_Ateendance.class.getSimpleName());

    }
    //push data to the database
    public Task<Void> add(Employee_M_Ateendance atda)
    {
        return databaseReference.push().setValue(atda);
    }
    //update the selected item in the database
    public Task<Void> update(String key1, HashMap<String,Object> hashMap)
    {
        return databaseReference.child(key1).updateChildren(hashMap);
    }
    //delete the selected item from the database
    public Task<Void> remove(String key1) {
        return databaseReference.child(key1).removeValue();
    }

    public Query get(String key1) {
        if (key1 == null) {
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key1).limitToFirst(8);
    }
    //display all items
    public Query get()
    {
        return databaseReference;
    }

}
