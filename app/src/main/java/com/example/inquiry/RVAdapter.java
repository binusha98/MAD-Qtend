package com.example.inquiry;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_itemi,parent,false);
        return new InquiryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        InquiryVH vh = (InquiryVH) holder;
        Inquiryn inqn = list.get(position);
        vh.editTextTextPersonName.setText(inqn.getEmployee_ID());
        vh.editTextTextPersonName2.setText(inqn.getEmployee_Name());
        vh.editTextTextEmailAddress.setText(inqn.getEmployee_Email());
        vh.editTextDate.setText(inqn.getEmployee_IDate());
        vh.radioButton.setText(inqn.getConfirmationInquiry());
        vh.radioButton2.setText(inqn.getGuidedInquiry());
        vh.radioButton3.setText(inqn.getOtherInquiry());
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
                        intent.putExtra("EDIT", (Parcelable) inqn);
                        context.startActivity(intent);
                        break;

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
