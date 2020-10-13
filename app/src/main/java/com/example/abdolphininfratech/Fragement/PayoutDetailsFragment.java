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
import com.example.abdolphininfratech.Adopter.PayoutDetailsAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.PayoutDetailsModal;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayoutDetailsFragment extends Fragment {

    private List<PayoutDetailsModal> payoutDetailsModals;
    private PayoutDetailsAdopter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    SearchView searchView;
    final String showUrl="http://test.abdolphininfratech.com//WebAPI/PayoutDetails";
    EditText editTextSearch;
    String UserID;
    String To_Date,From_Date,Payout_Number;
    String title="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payoutdetails_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerview1);
        progressDialog=new ProgressDialog(getContext());

        if (getArguments() != null) {

            To_Date = getArguments().getString("To_Date");
            From_Date = getArguments().getString("From_Date");
            Payout_Number=getArguments().getString("Payout_Number");
            // Status_Search = getArguments().getString("SpinnerValue");
            // Toast.makeText(getContext(), Status_Search + "", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getContext(), "Search Data Not Found", Toast.LENGTH_SHORT).show();
        }

        UserID= SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);

        payoutDetailsModals = new ArrayList<>();
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
        ArrayList<PayoutDetailsModal> filteredList = new ArrayList<>();
        for (PayoutDetailsModal item : payoutDetailsModals) {
            if (item.getFirstName().toLowerCase().contains(text.toLowerCase())|| item.getClosingDate().toLowerCase().contains(text.toLowerCase())||item.getLoginID().toLowerCase().contains(text.toLowerCase())) {
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
                    JSONArray jsonArray=jsonObject.getJSONArray("lstpayout");
                  //  Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PayoutDetailsModal payoutDetailsModal=new PayoutDetailsModal(jsonObject1.getString("ClosingDate"),jsonObject1.getString("PayOutNo"),
                                jsonObject1.getString("AssociateLoginID"),
                                jsonObject1.getString("FirstName"),jsonObject1.getString("GrossAmount"),jsonObject1.getString("Processing"),
                                jsonObject1.getString("NetAmount"));
                        payoutDetailsModals.add(payoutDetailsModal);
                    }
                    adapter = new PayoutDetailsAdopter(payoutDetailsModals, getContext());
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
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("UserID",UserID);
                parms.put("PayOutNo",Payout_Number);
               // parms.put("FromDate",From_Date);

               // parms.put("ToDate",To_Date);
                return parms;

            }
        };;
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

    }
