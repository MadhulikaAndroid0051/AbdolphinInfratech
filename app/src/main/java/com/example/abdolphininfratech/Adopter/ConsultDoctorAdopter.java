package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.ModelClass.Lstassociate;
import com.example.abdolphininfratech.R;

import java.util.List;

public class ConsultDoctorAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Lstassociate> doctConsultModoalList;
    Context context;

    public ConsultDoctorAdopter(List<Lstassociate> lstassociates, Context context) {
        this.doctConsultModoalList = lstassociates;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myholder = (ViewHolder) holder;
        final Lstassociate doctConsultModoal = doctConsultModoalList.get(position);

        myholder.Customer_ID.setText(doctConsultModoal.getCustomerID());
        myholder.Customer_name.setText(doctConsultModoal.getCustomerName());
        myholder.Installment_no.setText(doctConsultModoal.getInstallmentNo());
        myholder.Installment_Amount.setText(doctConsultModoal.getInstallmentAmount());
        myholder.Plot_details.setText(doctConsultModoal.getPlotNumber());


    }

    @Override
    public int getItemCount() {
        return doctConsultModoalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tomorrow_am, tv_tomorrow_pm;
        TextView sr_id, Customer_ID, Customer_name, Installment_no, Installment_Amount, Plot_details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Customer_ID = itemView.findViewById(R.id.customer_id);
            Customer_name = itemView.findViewById(R.id.customer_name);
            Installment_Amount = itemView.findViewById(R.id.Installment_Amount);
            Installment_no = itemView.findViewById(R.id.Installment_No);
            Plot_details = itemView.findViewById(R.id.Plot_Details);


        }
    }


}
/*public class ConsultDoctorAdopter extends RecyclerView.Adapter<ConsultDoctorAdopter.ViewHolder> {

    List<DoctConsultModoal>doctConsultModoalList;
    Context context;

    public ConsultDoctorAdopter(List<DoctConsultModoal> doctConsultModoalList, Context context) {
        this.doctConsultModoalList = doctConsultModoalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ConsultDoctorAdopter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_shift_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder myholder=(ViewHolder)holder;
         final DoctConsultModoal doctConsultModoal = doctConsultModoalList.get(position);
        holder.tv_tomorrow_am.setText(doctConsultModoal.getFromTime());
        holder.tv_tomorrow_pm.setText(doctConsultModoal.getToTime());

        myholder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
              Intent I=new Intent(context, BookingDetailsActivity.class);
              // I.putExtra("from_time",doctConsultModoal.getFromTime());
               //I.putExtra("to_time",doctConsultModoal.getToTime());
              // I.putExtra("date",doctConsultModoal.getDate());
               context.startActivity(I);

           }
        });


    }

    @Override
    public int getItemCount() {
        return doctConsultModoalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tomorrow_am,tv_tomorrow_pm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_tomorrow_am=itemView.findViewById(R.id.tv_tomorrow_am);
             tv_tomorrow_pm=itemView.findViewById(R.id.tv_tomorrow_pm);
        }
    }
    }*/
