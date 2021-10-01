package com.example.mad_app;

//import statements
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmployee_Attendance {

    private DatabaseReference databaseReference;

    public DAOEmployee_Attendance() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Employee_Attendance.class.getSimpleName());

    }
    //push data to the database
    public Task<Void> add(Employee_Attendance atd)
    {
        return databaseReference.push().setValue(atd);
    }
    //update the selected item in the database
    public Task<Void> update(String key, HashMap<String,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    //delete the selected item from the database
    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }


    public Query get(String key) {
        if (key == null) {
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }
    //display all items
    public Query get()
    {
        return databaseReference;
    }

}
