package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.ModelClass.PayoutDetailsModal;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;

public class PayoutDetailsAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    List<PayoutDetailsModal> payoutDetailsModals;
    private List<PayoutDetailsModal> exampleListFull;

    Context context;

    public PayoutDetailsAdopter(List<PayoutDetailsModal> payoutDetailsModals, Context context) {
        this.payoutDetailsModals = payoutDetailsModals;
        exampleListFull=new ArrayList<>(payoutDetailsModals);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payoutdetails, parent, false);
        return new PayoutDetailsAdopter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
PayoutDetailsAdopter.ViewHolder myholder =(PayoutDetailsAdopter.ViewHolder) holder;
        final PayoutDetailsModal payoutLedgerModal =payoutDetailsModals.get(position);
        myholder.ClosingDate.setText(payoutLedgerModal.getClosingDate());
        myholder.PayOutNo.setText(payoutLedgerModal.getPayOutNo());
        myholder.LoginId.setText(payoutLedgerModal.getLoginID());
        myholder.FirstName.setText(payoutLedgerModal.getFirstName());
        myholder.GrossAmount.setText(payoutLedgerModal.getGrossAmount());
        myholder.NetAmount.setText(payoutLedgerModal.getNetAmount());
        myholder.Processing.setText(payoutLedgerModal.getProcessing());

    }

    @Override
    public int getItemCount() {
        return payoutDetailsModals.size();
    }

    public void filterList(ArrayList<PayoutDetailsModal> filteredList) {
        payoutDetailsModals = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ClosingDate	,PayOutNo	,LoginId,FirstName,GrossAmount,Processing,NetAmount;
        Button action;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ClosingDate=itemView.findViewById(R.id.cloasing_date);
            PayOutNo=itemView.findViewById(R.id.payout_id);
            LoginId=itemView.findViewById(R.id.login_id);
            FirstName=itemView.findViewById(R.id.First_name);
            GrossAmount=itemView.findViewById(R.id.gross_amount);
            Processing=itemView.findViewById(R.id.processing);
            NetAmount=itemView.findViewById(R.id.netamount);
        }
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PayoutDetailsModal> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PayoutDetailsModal item : exampleListFull) {
                    if (item.getFirstName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            payoutDetailsModals.clear();
            payoutDetailsModals.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
