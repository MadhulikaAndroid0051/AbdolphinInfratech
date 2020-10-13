package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.ModelClass.SummaryReportModal;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;

public class SummaryReportAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SummaryReportModal> summaryReportModals;
    Context context;

    public SummaryReportAdopter(List<SummaryReportModal> summaryReportModals, Context context) {
        this.summaryReportModals = summaryReportModals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sumaryreport, parent, false);
        return new SummaryReportAdopter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SummaryReportAdopter.ViewHolder myholder =(SummaryReportAdopter.ViewHolder) holder;
        final  SummaryReportModal summaryReportModal=summaryReportModals.get(position);
        myholder.BranchName.setText(summaryReportModal.getBranchName());
        myholder.BookingNumber.setText(summaryReportModal.getBookingNumber());
        myholder.AffiliateInfo.setText(summaryReportModal.getAffiliateInfo());
        myholder.CustomerInfo.setText(summaryReportModal.getCustomerInfo());
        myholder.Plot.setText(summaryReportModal.getPlot());
        myholder.ActualPlotAmount.setText(summaryReportModal.getActualPlotAmount());
        myholder.NetPlotAmount.setText(summaryReportModal.getNetPlotAmount());
        myholder.BalanceAmount.setText(summaryReportModal.getBalanceAmount());
        myholder.PaymentDate.setText(summaryReportModal.getPaymentDate());


    }

    @Override
    public int getItemCount() {
        return summaryReportModals.size();
    }

    public void filterList(ArrayList<SummaryReportModal> filteredList) {
        summaryReportModals = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView BranchName,BookingNumber,AffiliateInfo,CustomerInfo,Plot,ActualPlotAmount,NetPlotAmount,BalanceAmount,PaymentDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BranchName=itemView.findViewById(R.id.branch_name);
            BookingNumber=itemView.findViewById(R.id.booking_number);
            AffiliateInfo=itemView.findViewById(R.id.affiliate_info);
            CustomerInfo=itemView.findViewById(R.id.customer_info);
            Plot=itemView.findViewById(R.id.plot);
            ActualPlotAmount=itemView.findViewById(R.id.actual_plot_amount);
            NetPlotAmount=itemView.findViewById(R.id.net_Plot_amount);
            BalanceAmount=itemView.findViewById(R.id.balance_amount);
            PaymentDate=itemView.findViewById(R.id.payment_date);
        }
    }
}
