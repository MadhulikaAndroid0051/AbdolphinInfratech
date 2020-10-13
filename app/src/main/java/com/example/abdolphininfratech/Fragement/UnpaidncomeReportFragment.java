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
import com.example.abdolphininfratech.Adopter.PayoutDetailsAdopter;
import com.example.abdolphininfratech.Adopter.UnpaidncomeReportAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.PayoutDetailsModal;
import com.example.abdolphininfratech.ModelClass.UnpaidncomeReportModel;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnpaidncomeReportFragment extends Fragment {
    RecyclerView recyclerView;
    private UnpaidncomeReportAdopter adapter;
    ProgressDialog progressDialog;
    private List<UnpaidncomeReportModel> unpaidncomeReportModels;
    EditText editTextSearch;
    String From_Date,To_Date,ToId,FromID,UserID;

  String title="Unpaid Income Report";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unpaid_income_report_fragment, container, false);
        UserID= SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);

        recyclerView=view.findViewById(R.id.recyclerview);
        editTextSearch=view.findViewById(R.id.search);
        From_Date=getArguments().getString("From_Date");
        To_Date=getArguments().getString("To_Date");
        FromID=getArguments().getString("FromID");
        ToId=getArguments().getString("ToID");


        progressDialog=new ProgressDialog(getContext());
         unpaidncomeReportModels= new ArrayList<>();
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
        ArrayList<UnpaidncomeReportModel> filteredList = new ArrayList<>();
        for (UnpaidncomeReportModel item : unpaidncomeReportModels) {
            if (item.getCosing_Date().toLowerCase().contains(text.toLowerCase())|| item.getFrom_ID().toLowerCase().contains(text.toLowerCase())
                    ||item.getDifferencePercentage().toLowerCase().contains(text.toLowerCase())||item.getBusiness_Amount().toLowerCase().contains(text.toLowerCase())
                    ||item.getFrom_Name().toLowerCase().contains(text.toLowerCase())||item.getIncome().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    public void getData()
    {
        String showUrl="http://test.abdolphininfratech.com/WebAPI/UnpaidIncome";
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstunpaid");
                    //  Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        UnpaidncomeReportModel unpaidncomeReportModel=new UnpaidncomeReportModel(jsonObject1.getString("FromDate"),jsonObject1.getString("FromID"),
                                jsonObject1.getString("FromName"),
                                jsonObject1.getString("ToID"),jsonObject1.getString("ToName"),jsonObject1.getString("Amount"),
                                jsonObject1.getString("DifferencePercentage"),jsonObject1.getString("Income"));
                        unpaidncomeReportModels.add(unpaidncomeReportModel);
                    }
                    adapter = new UnpaidncomeReportAdopter(unpaidncomeReportModels, getContext());
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
                parms.put("FromID",FromID);
              //  parms.put("FromDate",From_Date);
             //   parms.put("ToDate",To_Date);
                parms.put("ToID",ToId);

                return parms;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

}
