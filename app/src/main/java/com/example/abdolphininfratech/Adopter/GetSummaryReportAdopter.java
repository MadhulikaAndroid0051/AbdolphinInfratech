package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.ModelClass.GetSummaryReportModel;
import com.example.abdolphininfratech.ModelClass.Lstassociate;
import com.example.abdolphininfratech.ModelClass.PayoutDetailsModal;
import com.example.abdolphininfratech.R;

import java.util.List;

public class GetSummaryReportAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<GetSummaryReportModel> getSummaryReportModels;
    Context context;

    public GetSummaryReportAdopter(List<GetSummaryReportModel> getSummaryReportModels, Context context) {
        this.getSummaryReportModels = getSummaryReportModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.getsummary_report_item, parent, false);
        return new GetSummaryReportAdopter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GetSummaryReportAdopter.ViewHolder myholder =(GetSummaryReportAdopter.ViewHolder) holder;
        final GetSummaryReportModel payoutLedgerModal =getSummaryReportModels.get(position);
        myholder.BranchName.setText(payoutLedgerModal.getBranchName());
        myholder.BookingNumber.setText(payoutLedgerModal.getBookingNumber());
        myholder.AffiliateInfo.setText(payoutLedgerModal.getAffiliate_Info());
        myholder.CustomerInfo.setText(payoutLedgerModal.getCustomerName());
        myholder.Plot.setText(payoutLedgerModal.getPlotNumber());
        myholder.ActualPlotAmount.setText(payoutLedgerModal.getActualPlotAmount());
        myholder.NetPlotAmount.setText(payoutLedgerModal.getNetPlotAmount());
        myholder.BalanceAmount.setText(payoutLedgerModal.getBalanceAmount());
        myholder.PaymentDate.setText(payoutLedgerModal.getPaymentDate());

    }

    @Override
    public int getItemCount() {
        return getSummaryReportModels.size();
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
