package com.example.inquiry;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InquiryVH extends RecyclerView.ViewHolder {

    public TextView editTextTextPersonName,editTextTextPersonName2,editTextTextEmailAddress,editTextDate,editTextNumber,editTextTextMultiLine,txt_option;


    public InquiryVH(@NonNull View itemView)
    {
        super(itemView);
        editTextTextPersonName = itemView.findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = itemView.findViewById(R.id.editTextTextPersonName2);
        editTextTextEmailAddress = itemView.findViewById(R.id.editTextTextEmailAddress);
        editTextDate = itemView.findViewById(R.id.editTextDate);
        editTextNumber = itemView.findViewById(R.id.editTextNumber);
        editTextTextMultiLine = itemView.findViewById(R.id.editTextTextMultiLine);
        txt_option = itemView.findViewById(R.id.txt_option);

    }
}
