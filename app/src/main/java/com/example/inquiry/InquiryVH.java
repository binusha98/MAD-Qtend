package com.example.inquiry;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InquiryVH extends RecyclerView.ViewHolder {

    public TextView editTextTextPersonName,editTextTextPersonName2,editTextTextEmailAddress,editTextDate,radioButton,radioButton2,radioButton3,editTextTextMultiLine,txt_option;


    public InquiryVH(@NonNull View itemView) {
        super(itemView);
        editTextTextPersonName = itemView.findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = itemView.findViewById(R.id.editTextTextPersonName2);
        editTextTextEmailAddress = itemView.findViewById(R.id.editTextTextEmailAddress);
        editTextDate = itemView.findViewById(R.id.editTextDate);
        radioButton = itemView.findViewById(R.id.radioButton);
        radioButton2 = itemView.findViewById(R.id.radioButton2);
        radioButton3 = itemView.findViewById(R.id.radioButton3);
        editTextTextMultiLine = itemView.findViewById(R.id.editTextTextMultiLine);
        txt_option = itemView.findViewById(R.id.txt_option);

    }
}
