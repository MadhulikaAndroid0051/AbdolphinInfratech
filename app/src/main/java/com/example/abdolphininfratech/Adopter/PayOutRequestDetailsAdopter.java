package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.ModelClass.PayOutRequestDetailsModel;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;

public class PayOutRequestDetailsAdopter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<PayOutRequestDetailsModel> payOutRequestDetailsModels;
    Context context;

    public PayOutRequestDetailsAdopter(List<PayOutRequestDetailsModel> payOutRequestDetailsModels, Context context) {
        this.payOutRequestDetailsModels = payOutRequestDetailsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payoutrequestrepot_itrm, parent, false);
        return new PayOutRequestDetailsAdopter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PayOutRequestDetailsAdopter.ViewHolder myholder =(PayOutRequestDetailsAdopter.ViewHolder) holder;
        final PayOutRequestDetailsModel payOutRequestDetailsModel =payOutRequestDetailsModels.get(position);
        myholder.req_LoginId.setText(payOutRequestDetailsModel.getReq_LoginID());
        myholder.req_FirstName.setText(payOutRequestDetailsModel.getReq_FirstName());
        myholder.req_requestDate.setText(payOutRequestDetailsModel.getReq_RequestedDate());
        myholder.req_amount.setText(payOutRequestDetailsModel.getReq_Amount());
        myholder.req_status.setText(payOutRequestDetailsModel.getReq_status());

    }

    @Override
    public int getItemCount() {
        return payOutRequestDetailsModels.size();
    }

    public void filterList(ArrayList<PayOutRequestDetailsModel> filteredList) {

        payOutRequestDetailsModels = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView req_LoginId,req_FirstName,req_requestDate,req_amount,req_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            req_LoginId=itemView.findViewById(R.id.req_loginId);
            req_FirstName=itemView.findViewById(R.id.req_firstname);
            req_requestDate=itemView.findViewById(R.id.req_requested_date);
            req_amount=itemView.findViewById(R.id.req_amount);
            req_status=itemView.findViewById(R.id.req_status);
        }
    }
    }
