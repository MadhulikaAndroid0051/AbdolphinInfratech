package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.Fragement.PayoutLedgerModal;
import com.example.abdolphininfratech.ModelClass.AssociateDownlineDetailModel;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;

public class AssociateDownlineDetailAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<AssociateDownlineDetailModel> associateDownlineDetailModelList;

    Context context;

    public AssociateDownlineDetailAdopter(List<AssociateDownlineDetailModel> associateDownlineDetailModelList, Context context) {
        this.associateDownlineDetailModelList = associateDownlineDetailModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.associatedownlinedetails, parent, false);
        return new AssociateDownlineDetailAdopter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AssociateDownlineDetailAdopter.ViewHolder myholder = (AssociateDownlineDetailAdopter.ViewHolder) holder;
        final AssociateDownlineDetailModel associateDownlineDetailModel = associateDownlineDetailModelList.get(position);

        myholder.affiliate.setText(associateDownlineDetailModel.getAffiliateID());
        myholder.affiliate_name.setText(associateDownlineDetailModel.getAffiliateName());
        myholder.rank.setText(associateDownlineDetailModel.getRank());
        myholder.rank_name.setText(associateDownlineDetailModel.getRankName());
        myholder.mobile_number.setText(associateDownlineDetailModel.getContact());

    }

    @Override
    public int getItemCount() {
        return associateDownlineDetailModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView affiliate, affiliate_name, rank_name, rank, branch_name, mobile_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            affiliate = itemView.findViewById(R.id.affiliate);
            affiliate_name = itemView.findViewById(R.id.affiliate_name);
            rank_name = itemView.findViewById(R.id.rank_name);
            rank = itemView.findViewById(R.id.rank);
            mobile_number = itemView.findViewById(R.id.mobile_number);
        }
    }

    public void filterList(ArrayList<AssociateDownlineDetailModel> filteredList) {
        associateDownlineDetailModelList = filteredList;
        notifyDataSetChanged();

    }
}