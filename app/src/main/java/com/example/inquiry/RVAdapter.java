package com.example.inquiry;

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

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    ArrayList<Inquiryn> list = new ArrayList<>();
    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Inquiryn> inqn)
    {

        list.addAll(inqn);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_itemi,parent,false);
        return new InquiryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        Inquiryn e = null;
        this.onBindViewHolder(holder,position,e);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Inquiryn e)
    {

        InquiryVH vh = (InquiryVH) holder;
        Inquiryn inqn = e==null? list.get(position):e;
        vh.editTextTextPersonName.setText(inqn.getEmployee_ID());
        vh.editTextTextPersonName2.setText(inqn.getEmployee_Name());
        vh.editTextTextEmailAddress.setText(inqn.getEmployee_Email());
        vh.editTextDate.setText(inqn.getEmployee_IDate());
        vh.editTextNumber.setText(inqn.getInquiryType());
        vh.editTextTextMultiLine.setText(inqn.getIDescription());
        vh.txt_option.setOnClickListener(v ->
        {
            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.optioni_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context,AddInquiry.class);
                        intent.putExtra("EDIT", inqn);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOInquiryn dao = new DAOInquiryn();
                        dao.remove(inqn.getKey()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context,"Record is Removed",Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            list.remove(inqn);
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
