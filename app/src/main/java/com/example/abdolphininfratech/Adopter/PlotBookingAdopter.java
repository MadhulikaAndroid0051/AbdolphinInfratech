package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.Fragement.PayoutLedgerModal;
import com.example.abdolphininfratech.ModelClass.PlotBookingModel;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;

public class PlotBookingAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<PlotBookingModel> plotBookingAdopters;
    Context context;

    public PlotBookingAdopter(List<PlotBookingModel> plotBookingAdopters, Context context) {
        this.plotBookingAdopters = plotBookingAdopters;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plotbooking_item, parent, false);
        return new PlotBookingAdopter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlotBookingAdopter.ViewHolder myholder =(PlotBookingAdopter.ViewHolder) holder;
        final PlotBookingModel plotBookingModel=plotBookingAdopters.get(position);

        myholder.tv_branch_name.setText(plotBookingModel.getBranch_name());
        myholder.tv_booking_no.setText(plotBookingModel.getBooking_no());
        myholder.tv_booking_date.setText(plotBookingModel.getBooking_date());
        myholder.tv_plot_details.setText(plotBookingModel.getPlot_details());
        myholder.tv_payment_plan.setText(plotBookingModel.getPayment_plan());
        myholder.tv_customer_detail.setText(plotBookingModel.getCustomer_details());
        myholder.tv_affiliate_id.setText(plotBookingModel.getAffiliate_details());
        myholder.tv_plot_amt.setText(plotBookingModel.getPlot_amt());
        myholder.tv_plc.setText(plotBookingModel.getPLC());
        myholder.tv_net_amnt.setText(plotBookingModel.getNet_amnt());
        myholder.tv_paid_amt.setText(plotBookingModel.getPaid_amnt());

    }

    @Override
    public int getItemCount() {
        return plotBookingAdopters.size();
    }
    public void filterList(ArrayList<PlotBookingModel> filteredList) {
        plotBookingAdopters = filteredList;
        notifyDataSetChanged();

    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_branch_name,tv_booking_no,tv_booking_date,tv_plot_details,tv_payment_plan,tv_customer_detail,tv_affiliate_id,tv_plot_amt,
                tv_plc,tv_net_amnt,tv_paid_amt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_branch_name=itemView.findViewById(R.id.tv_branch_name);
            tv_booking_no=itemView.findViewById(R.id.tv_booking_no);
            tv_booking_date=itemView.findViewById(R.id.tv_booking_date);
            tv_plot_details=itemView.findViewById(R.id.tv_plot_details);
            tv_payment_plan=itemView.findViewById(R.id.tv_payment_plan);
            tv_customer_detail=itemView.findViewById(R.id.tv_customer_detail);
            tv_affiliate_id=itemView.findViewById(R.id.tv_affiliate_id);
            tv_plot_amt=itemView.findViewById(R.id.tv_plot_amt);
            tv_plc=itemView.findViewById(R.id.tv_plc);
            tv_net_amnt=itemView.findViewById(R.id.tv_net_amnt);
            tv_paid_amt=itemView.findViewById(R.id.tv_paid_amt);
        }


    }
}
