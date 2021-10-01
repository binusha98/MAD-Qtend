package com.example.mad_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//import statements
public class RVAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    ArrayList<Employee_M_Ateendance> list = new ArrayList<>();
    public RVAdapter1(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Employee_M_Ateendance> atda)
    {
        list.addAll(atda);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item1,parent,false);
        return new MarkVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        Employee_M_Ateendance f = null;
        this.onBindViewHolder(holder,position,f);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Employee_M_Ateendance f)
    {
        MarkVH vh = (MarkVH) holder;
        Employee_M_Ateendance atda = f==null? list.get(position):f;
        vh.txt_ida.setText(atda.getidA());
        vh.txt_date.setText(atda.getdate());
        vh.txt_option1.setOnClickListener(v->
        {
            // creating menu for each item to update and remove in recycler view

            PopupMenu popupMenu =new PopupMenu(context,vh.txt_option1);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent=new Intent(context,attendance.class);
                        intent.putExtra("EDIT",atda);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAO_Attendance daoa=new DAO_Attendance();
                        daoa.remove(atda.getKey1()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            list.remove(atda);
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }



    @Override
    public int getItemCount()
    {
        return list.size();
    }
}


