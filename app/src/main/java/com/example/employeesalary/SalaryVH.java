
package com.example.employeesalary;

        import android.view.View;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;


public class SalaryVH extends RecyclerView.ViewHolder
{
    public TextView txt_name,txt_designation,txt_basicSalary,txt_deduction,txt_bonus,txt_totalSalary,txt_option;


    public SalaryVH(@NonNull View itemView)
    {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_designation = itemView.findViewById(R.id.txt_designation);
        txt_basicSalary = itemView.findViewById(R.id.txt_basicSalary);
        txt_deduction = itemView.findViewById(R.id.txt_deduction);
        txt_bonus = itemView.findViewById(R.id.txt_bonus);
        txt_totalSalary = itemView.findViewById(R.id.txt_totalSalary);
        txt_option = itemView.findViewById(R.id.txt_option);


    }
}
