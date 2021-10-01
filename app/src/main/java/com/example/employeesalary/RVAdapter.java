package com.example.employeesalary;

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
    ArrayList<Employee_Salary> list = new ArrayList<>();
    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Employee_Salary> atd)
    {
        list.addAll(atd);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new SalaryVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        Employee_Salary e = null;
        this.onBindViewHolder(holder,position,e);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Employee_Salary e)
    {
        SalaryVH vh = (SalaryVH) holder;
        Employee_Salary atd = e==null? list.get(position):e;
        vh.txt_name.setText(e.getName());
        vh.txt_designation.setText(e.getDesignation());
        vh.txt_basicSalary.setText(e.getBasic_salary());
        vh.txt_deduction.setText(e.getDeductions());
        vh.txt_bonus.setText(e.getBonus());
        vh.txt_totalSalary.setText(e.getTotal_salary());
        vh.txt_option.setOnClickListener(v->
        {
            PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent=new Intent(context,salary.class);
                        intent.putExtra("EDIT",atd);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOEmployee_Salary dao=new DAOEmployee_Salary();
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
