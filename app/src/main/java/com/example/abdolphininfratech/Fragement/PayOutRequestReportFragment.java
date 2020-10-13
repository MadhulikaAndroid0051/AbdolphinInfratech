package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdolphininfratech.Adopter.PayOutRequestDetailsAdopter;
import com.example.abdolphininfratech.Adopter.PayoutLedgerAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.PayOutRequestDetailsModel;
import com.example.abdolphininfratech.ModelClass.UnpaidncomeReportModel;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayOutRequestReportFragment extends Fragment {
    RecyclerView recyclerView;
    private List<PayOutRequestDetailsModel> payOutRequestDetailsModels;
    private PayOutRequestDetailsAdopter adapter;
    ProgressDialog progressDialog;
    EditText editTextSearch;
    String UserID,To_Date,From_Date,Status_Search;
    String title="PayOut Request Report";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payoutreportrequest_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        editTextSearch=view.findViewById(R.id.search);
        if (getArguments() != null) {

            To_Date = getArguments().getString("To_Date");
            From_Date = getArguments().getString("From_Date");
           // Status_Search = getArguments().getString("SpinnerValue");
           // Toast.makeText(getContext(), Status_Search + "", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getContext(), "Not Found Data", Toast.LENGTH_SHORT).show();
        }
        UserID= SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);

        progressDialog=new ProgressDialog(getContext());
        payOutRequestDetailsModels = new ArrayList<>();
        //  recyclerView.setAdapter(adapter);
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
        ArrayList<PayOutRequestDetailsModel> filteredList = new ArrayList<>();
        for (PayOutRequestDetailsModel item : payOutRequestDetailsModels) {
            if (item.getReq_LoginID().toLowerCase().contains(text.toLowerCase())|| item.getReq_Amount().toLowerCase().contains(text.toLowerCase())
                    ||item.getReq_FirstName().toLowerCase().contains(text.toLowerCase())||item.getReq_FirstName().toLowerCase().contains(text.toLowerCase())
                    ||item.getReq_status().toLowerCase().contains(text.toLowerCase())||item.getReq_RequestedDate().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    public void getData()
    {
        String showUrl="http://test.abdolphininfratech.com/WebAPI/PayoutRequestReport";
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstpayout");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PayOutRequestDetailsModel payOutRequestDetailsModel=new PayOutRequestDetailsModel(jsonObject1.getString("AssociateLoginID"),
                                jsonObject1.getString("FirstName"),jsonObject1.getString("ClosingDate"),jsonObject1.getString("GrossAmount"),jsonObject1.getString("Status"));
                        payOutRequestDetailsModels.add(payOutRequestDetailsModel);
                    }
                    adapter=new PayOutRequestDetailsAdopter(payOutRequestDetailsModels,getContext());
                    recyclerView.setAdapter(adapter);


                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
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
                parms.put("FromDate",From_Date);
                parms.put("ToDate",To_Date);
                //parms.put("Status","Approved");
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
