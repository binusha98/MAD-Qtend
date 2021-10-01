package com.example.inquiry;

//import android.annotation.SuppressLint;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    //FirebaseRecyclerAdapter adapter_;

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

        //FirebaseRecyclerOptions<Inquiryn> option = new FirebaseRecyclerOptions.Builder<Inquiryn>().setQuery(dao.get(), new SnapshotParser<Inquiryn>() {
           // @NonNull
           // @Override
           // public Inquiryn parseSnapshot(@NonNull DataSnapshot snapshot)
           // {
               // Inquiryn inqn = snapshot.getValue(Inquiryn.class);
              //  inqn.setKey(snapshot.getKey());
              //  return inqn;
            //}
        //}).build();

        //adapter_ = new FirebaseRecyclerAdapter(option) {
           // @Override
           // protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull Object o)
           // {
                //InquiryVH vh = (InquiryVH) viewHolder;
               // Inquiryn inqn = (Inquiryn)o;
               // vh.editTextTextPersonName.setText(inqn.getEmployee_ID());
               // vh.editTextTextPersonName2.setText(inqn.getEmployee_Name());
               // vh.editTextTextEmailAddress.setText(inqn.getEmployee_Email());
               // vh.editTextDate.setText(inqn.getEmployee_IDate());
               // vh.radioButton.setText(inqn.getConfirmationInquiry());
               // vh.radioButton2.setText(inqn.getGuidedInquiry());
               // vh.radioButton3.setText(inqn.getOtherInquiry());
              //  vh.editTextTextMultiLine.setText(inqn.getIDescription());
              //  vh.txt_option.setOnClickListener(v ->
               // {
                   // PopupMenu popupMenu = new PopupMenu(SearchInquiries.this,vh.txt_option);
                   // popupMenu.inflate(R.menu.optioni_menu);
                   // popupMenu.setOnMenuItemClickListener(item->
                   // {
                      //  switch (item.getItemId())
                      //  {
                         //   case R.id.menu_edit:
                             //   Intent intent = new Intent(SearchInquiries.this,AddInquiry.class);
                             //   intent.putExtra("EDIT", (Parcelable) inqn);
                             //   startActivity(intent);
                            //    break;
                         //   case R.id.menu_remove:
                             //   DAOInquiryn dao = new DAOInquiryn();
                              //  dao.remove(inqn.getKey()).addOnSuccessListener(suc->
                              //  {
                              //      Toast.makeText(SearchInquiries.this,"Record is Removed",Toast.LENGTH_SHORT).show();

                             //   }).addOnFailureListener(er->
                               // {
                               //     Toast.makeText(SearchInquiries.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                              //  });
                                //break;

                       // }
                      //  return false;
                   // });
                  //  popupMenu.show();
               // });
           // }

           // @NonNull
           // @Override
           // public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
              //  View view = LayoutInflater.from(SearchInquiries.this).inflate(R.layout.layout_itemi,parent,false);
              //  return new InquiryVH(view);
         //   }

           // @Override
           // public void onDataChanged() {
              //  Toast.makeText(SearchInquiries.this, "Data Changed", Toast.LENGTH_SHORT).show();
           // }
       // };
       //
///////////////////////////////////////////////////////////////////////////////////////////
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
        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener() {


            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<Inquiryn> inqns = new ArrayList<>();
                for (DataSnapshot data :snapshot.getChildren())
                {
                    Inquiryn inqn = data.getValue(Inquiryn.class);
                    inqn.setKey(data.getKey());
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

   // @Override
   // protected void onStart()
    //{
       // super.onStart();
   //    // adapter_.startListening();
   // }
   // @Override
   // protected void onStop()
   // {
     //   super.onStop();
     //   adapter_.stopListening();
  //  }
}