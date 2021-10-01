//view handler of attendance details
package com.example.mad_app;

//import statements
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class AttendanceVH extends RecyclerView.ViewHolder
{
    public TextView txt_id,txt_name,txt_ot,txt_wd,txt_npd,txt_option;


    public AttendanceVH(@NonNull View itemView)
    {
        //referencing by layout_item.xml file
        super(itemView);
        txt_id = itemView.findViewById(R.id.txt_id);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_ot = itemView.findViewById(R.id.txt_ot);
        txt_wd = itemView.findViewById(R.id.txt_wd);
        txt_npd = itemView.findViewById(R.id.txt_npd);
        txt_option = itemView.findViewById(R.id.txt_option);


    }
}
