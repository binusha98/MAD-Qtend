package com.example.mad_app;

//import statements
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


public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    ArrayList<Employee_Attendance> list = new ArrayList<>();
    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Employee_Attendance> atd)
    {
        list.addAll(atd);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new AttendanceVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        Employee_Attendance e = null;
        this.onBindViewHolder(holder,position,e);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Employee_Attendance e)
    {   //set tha values from database to view holder
        AttendanceVH vh = (AttendanceVH) holder;
        Employee_Attendance atd = e==null? list.get(position):e;
        vh.txt_id.setText(atd.getId());
        vh.txt_name.setText(atd.getName());
        vh.txt_ot.setText(atd.getOT_hrs());
        vh.txt_wd.setText(atd.getWork_days());
        vh.txt_npd.setText(atd.getNo_pay_days());
        vh.txt_option.setOnClickListener(v->
        {
            // creating menu for each item to update and remove in recycler view

            PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent=new Intent(context,attendance.class);
                        intent.putExtra("EDIT",atd);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOEmployee_Attendance dao=new DAOEmployee_Attendance();
                        dao.remove(atd.getKey()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            list.remove(atd);
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

