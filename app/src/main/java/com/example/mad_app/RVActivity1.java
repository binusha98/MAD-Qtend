package com.example.mad_app;

//recycler view activity import statements
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RVActivity1 extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout1;
    RecyclerView recyclerView1;
    RVAdapter1 adapter1;
    DAO_Attendance daoa;
    boolean isLoading=false;
    String key1 =null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvactivity1);
        swipeRefreshLayout1 = findViewById(R.id.swip1);
        recyclerView1 = findViewById(R.id.rv1);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(manager);
        adapter1= new RVAdapter1(this);
        recyclerView1.setAdapter(adapter1);

        //creating database access object
        daoa = new DAO_Attendance();
        loadData();

        //recycler view modification to load data
        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem< lastVisible+6)
                {
                    if(!isLoading)
                    {
                        isLoading=true;
                        loadData();
                    }
                }
            }
        });
    }
    private void loadData()
    {
        //load data after refreshing the recycler view
        swipeRefreshLayout1.setRefreshing(true);
        daoa.get(key1).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<Employee_M_Ateendance> atdas = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    Employee_M_Ateendance atda = data.getValue(Employee_M_Ateendance.class);
                    atda.setKey1(data.getKey());
                    atdas.add(atda);
                    key1 = data.getKey();
                }
                adapter1.setItems(atdas);
                adapter1.notifyDataSetChanged();
                isLoading =false;
                swipeRefreshLayout1.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                swipeRefreshLayout1.setRefreshing(false);
            }
        });
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
            Intent updateIntent = new Intent(RVActivity1.this,attendance.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_update){
            Intent updateIntent = new Intent(RVActivity1.this,Update_Attendance.class);
            startActivity(updateIntent);
        }
        if (id == R.id.action_delete){
            Intent deleteIntent = new Intent(RVActivity1.this,Delete_Attendance.class);
            startActivity(deleteIntent);
        }
        if (id == R.id.action_mark){
            Intent markIntent = new Intent(RVActivity1.this,Mark_Attendance.class);
            startActivity(markIntent);
        }
        if (id == R.id.app_bar_search){
            Intent searchIntent = new Intent(RVActivity1.this,Search.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }



}