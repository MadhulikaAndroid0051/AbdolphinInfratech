package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
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
import com.example.abdolphininfratech.Adopter.GetSummaryReportAdopter;
import com.example.abdolphininfratech.Adopter.SummaryReportAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.GetSummaryReportModel;
import com.example.abdolphininfratech.ModelClass.SummaryReportModal;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetSummaryReportFragment extends Fragment {
    private List<GetSummaryReportModel>getSummaryReportModels;
    private GetSummaryReportAdopter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    String LoginId;
    String title="Summary Report";
    String search_customerId,search_customerName,search_mobileNumber,search_bookingNumber,search_plotNumber,From_Date,To_Date,
            PK_SiteID,PK_SectorID,PK_BlockID;
    SearchView searchView;
    final String showUrl="http://test.abdolphininfratech.com/WebAPI/GetSummaryReport";
    EditText editTextSearch;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.getsummary_report_fragment, container, false);
        LoginId= SessionHandler.getInstance().get(getContext(), PerferencesUtility.LoginID);

      //  Toast.makeText(getContext(),LoginId+ "", Toast.LENGTH_SHORT).show();

        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressDialog=new ProgressDialog(getContext());
        getSummaryReportModels= new ArrayList<>();

        search_customerId=getArguments().getString("Search_CustomerID");
        search_customerName=getArguments().getString("Search_CustomerName");
        search_mobileNumber=getArguments().getString("Search_MobileNumber");
        search_bookingNumber=getArguments().getString("Search_BookingNumber");
        search_plotNumber=getArguments().getString("Search_PlotNumber");
        From_Date=getArguments().getString("From_Date");
        To_Date=getArguments().getString("To_Date");
        PK_SiteID=getArguments().getString("PK_SiteID");
        PK_SectorID=getArguments().getString("PK_SectorID");
        PK_BlockID=getArguments().getString("PK_BlockID");

        getData();
        return view;
    }
    public void getData()
    {
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstSummary");
                  //  Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        GetSummaryReportModel summaryReportModal=new GetSummaryReportModel(jsonObject1.getString("BranchName"),jsonObject1.getString("BookingNumber"),
                                jsonObject1.getString("AssociateName"),
                                jsonObject1.getString("CustomerName"),jsonObject1.getString("PlotNumber"),jsonObject1.getString("PlotAmount"),
                                jsonObject1.getString("Amount"),jsonObject1.getString("Balance"),jsonObject1.getString("PaymentDate"));
                        getSummaryReportModels.add(summaryReportModal);
                    }
                    adapter = new GetSummaryReportAdopter(getSummaryReportModels, getContext());
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
                parms.put("AssociateID",LoginId);
                parms.put("FromDate",From_Date);
                parms.put("ToDate",To_Date);
                parms.put("CustomerName",search_customerName);
                parms.put("Mobile",search_mobileNumber);
                parms.put("PlotNumber",search_plotNumber);
                parms.put("BookingNumber",search_bookingNumber);

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
