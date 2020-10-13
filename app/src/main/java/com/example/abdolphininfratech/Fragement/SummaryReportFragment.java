package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdolphininfratech.Adopter.SummaryReportAdopter;
import com.example.abdolphininfratech.ModelClass.PlotBookingModel;
import com.example.abdolphininfratech.ModelClass.SummaryReportModal;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SummaryReportFragment  extends Fragment {

    private List<SummaryReportModal> summaryReportModalList;
    private SummaryReportAdopter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    SearchView searchView;
    final String showUrl="http://test.abdolphininfratech.com/WebAPI/BookingList?LoginId=ADI74690";
    EditText editTextSearch;
    String title="Summary Report";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.summaryreport_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        progressDialog=new ProgressDialog(getContext());
        summaryReportModalList= new ArrayList<>();
        editTextSearch=view.findViewById(R.id.search);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //adapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString() );

            }
        });

        getData();
        return view;
    }
    private void filter(String text) {
        ArrayList<SummaryReportModal> filteredList = new ArrayList<>();
        for (SummaryReportModal item : summaryReportModalList) {
            if (item.getBranchName().toLowerCase().contains(text.toLowerCase())||
                    item.getActualPlotAmount().toLowerCase().contains(text.toLowerCase())||
                    item.getAffiliateInfo().toLowerCase().contains(text.toLowerCase())||item.getBookingNumber().contains(text.toLowerCase())||item.getCustomerInfo()
                    .toLowerCase().contains(text.toLowerCase())||item.getNetPlotAmount().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    public void getData()
    {
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstbooking");
                  //  Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        SummaryReportModal summaryReportModal=new SummaryReportModal(jsonObject1.getString("BranchName"),jsonObject1.getString("BookingNumber"),
                                jsonObject1.getString("AssociateName"),
                                jsonObject1.getString("CustomerID"),jsonObject1.getString("PlotArea"),jsonObject1.getString("PlotAmount"),
                                jsonObject1.getString("NetPlotAmount"),jsonObject1.getString("PaidAmount"),jsonObject1.getString("BookingDate"));
                        summaryReportModalList.add(summaryReportModal);
                    }
                    adapter = new SummaryReportAdopter(summaryReportModalList, getContext());
                    recyclerView.setAdapter(adapter);


                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error+ "", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

}
