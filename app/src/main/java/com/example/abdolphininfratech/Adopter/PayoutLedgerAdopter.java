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
import com.example.abdolphininfratech.ModelClass.PayoutDetailsModal;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PayoutLedgerAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<PayoutLedgerModal> payoutLedgerModals;
    Context context;


    public PayoutLedgerAdopter(List<PayoutLedgerModal> payoutLedgerModals, Context context) {
        this.payoutLedgerModals = payoutLedgerModals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payoutledger, parent, false);
        return new PayoutLedgerAdopter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myholder = (ViewHolder) holder;
        final PayoutLedgerModal payoutLedgerModal = payoutLedgerModals.get(position);
        myholder.Transaction_Date.setText(payoutLedgerModal.getTransactionDate());
        myholder.credit_amount.setText(payoutLedgerModal.getCreditAmount());
        myholder.narration.setText(payoutLedgerModal.getNarration());
        myholder.debit.setText(payoutLedgerModal.getDebit());

    }

    @Override
    public int getItemCount() {
        return payoutLedgerModals.size();
    }

   /* @Override
    public Filter getFilter() {
        return null;
    }

    public class NewFilter extends Filter {
        public PayoutLedgerAdopter mAdapter;
        public NewFilter(PayoutLedgerAdopter mAdapter){
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            payoutLedgerModals.clear();
            final FilterResults results = new FilterResults();
            if(charSequence.length() == 0){
                payoutLedgerModals.addAll(payoutLedgerModals);
            }else{
                final String filterPattern =charSequence.toString().toLowerCase().trim();
                for(PayoutLedgerModal listcountry :payoutLedgerModals){
                    if(listcountry.getNarration().toLowerCase().startsWith(filterPattern)){
                        payoutLedgerModals.add(listcountry);
                    }
                }
            }
            results.values = payoutLedgerModals;
            results.count = payoutLedgerModals.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            this.mAdapter.notifyDataSetChanged();
        }
    }*/


    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView Transaction_Date,narration,credit_amount,debit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Transaction_Date=itemView.findViewById(R.id.Transaction_Date);
            narration=itemView.findViewById(R.id.narration);
            credit_amount=itemView.findViewById(R.id.Credit_amount);
            debit=itemView.findViewById(R.id.debit);
        }

    }
    public void filterList(ArrayList<PayoutLedgerModal> filteredList) {
        payoutLedgerModals = filteredList;
        notifyDataSetChanged();
    }
    }
