package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.ModelClass.PlotBookingModel;
import com.example.abdolphininfratech.ModelClass.UnpaidncomeReportModel;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;

public class UnpaidncomeReportAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UnpaidncomeReportModel> unpaidncomeReportModels;
    Context context;

    public UnpaidncomeReportAdopter(List<UnpaidncomeReportModel> unpaidncomeReportModels, Context context) {
        this.unpaidncomeReportModels = unpaidncomeReportModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.unpaid_income_report_item, parent, false);
        return new UnpaidncomeReportAdopter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UnpaidncomeReportAdopter.ViewHolder myholder =(UnpaidncomeReportAdopter.ViewHolder) holder;
        final UnpaidncomeReportModel unpaidncomeReportModel=unpaidncomeReportModels.get(position);

        myholder.cloasing_date.setText(unpaidncomeReportModel.getCosing_Date());
        myholder.from_id.setText(unpaidncomeReportModel.getFrom_ID());
        myholder.from_name.setText(unpaidncomeReportModel.getFrom_Name());
        myholder.to_id.setText(unpaidncomeReportModel.getTo_ID());
        myholder.to_name.setText(unpaidncomeReportModel.getTo_Name());
        myholder.business_amount.setText(unpaidncomeReportModel.getBusiness_Amount());
        myholder.difference_percentage.setText(unpaidncomeReportModel.getDifferencePercentage());
        myholder.income.setText(unpaidncomeReportModel.getIncome());

    }

    @Override
    public int getItemCount() {
        return unpaidncomeReportModels.size();
    }

    public void filterList(ArrayList<UnpaidncomeReportModel> filteredList) {
        unpaidncomeReportModels = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cloasing_date,from_id,from_name,to_id,to_name,business_amount,difference_percentage,income;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cloasing_date=itemView.findViewById(R.id.cloasing_date);
            from_id=itemView.findViewById(R.id.from_id);
            from_name=itemView.findViewById(R.id.from_name);
            to_id=itemView.findViewById(R.id.to_id);
            to_name=itemView.findViewById(R.id.to_name);
            business_amount=itemView.findViewById(R.id.business_amount);
            difference_percentage=itemView.findViewById(R.id.difference_percentage);
            income=itemView.findViewById(R.id.income);
        }
    }
    }
