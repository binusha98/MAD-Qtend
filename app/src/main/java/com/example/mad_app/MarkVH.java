package com.example.mad_app;
//import statements
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MarkVH extends RecyclerView.ViewHolder
{
    public TextView txt_ida,txt_date,txt_option1;


    public MarkVH(@NonNull View itemView)
    {
        //referencing by layout_item1.xml file
        super(itemView);
        txt_ida = itemView.findViewById(R.id.txt_ida);
        txt_date = itemView.findViewById(R.id.txt_date);
        txt_option1 = itemView.findViewById(R.id.txt_option1);



    }
}
