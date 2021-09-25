package com.example.inquiry;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchInquiries extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOInquiryn dao;
    boolean isLoading=false;
    String key =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_inquiries);
        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.irv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter= new RVAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOInquiryn();
        loadData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem< lastVisible+3)
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

    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<Inquiryn> inqns = new ArrayList<>();
                for (DataSnapshot data :snapshot.getChildren())
                {
                    data.getValue(Inquiryn.class);
                    inqns.add(inqn);
                    key = data.getKey();
                }
                adapter.setItems(inqns);
                adapter.notifyDataSetChanged();
                isLoading =false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
        if (id == R.id.textView22){

        }

        if (id == R.id.textView15){
            Intent updateIntent = new Intent(SearchInquiries.this,DeleteInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView){
            Intent updateIntent = new Intent(SearchInquiries.this,AddInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView8){
            Intent updateIntent = new Intent(SearchInquiries.this,EditInquiry.class);
            startActivity(updateIntent);
        }

        if (id == R.id.textView23){
            Intent updateIntent = new Intent(SearchInquiries.this,MyInquiry.class);
            startActivity(updateIntent);
        }
        if (id == R.id.textView43){
            Intent updateIntent = new Intent(SearchInquiries.this,HowAboutOurService.class);
            startActivity(updateIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}