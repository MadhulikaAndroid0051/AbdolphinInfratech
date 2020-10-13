package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.example.abdolphininfratech.Adopter.PlotAvailabilityAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.PlotAvailabilityModel;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlotAvailabilityDetailsFragment extends Fragment {

    private List<PlotAvailabilityModel> plotAvailabilityModels;
    private PlotAvailabilityAdopter adapter;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    EditText editTextSearch;
    String PK_SiteTypeID,PK_SiteID,PK_BlockID,PK_SectorID,PlotNumber;
    String title="Plot Availability";

    String LoginId;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plotavailabitydetails_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        editTextSearch=view.findViewById(R.id.search);
        LoginId= SessionHandler.getInstance().get(getContext(), PerferencesUtility.LoginID);

        PK_SiteTypeID=getArguments().getString("PK_SiteTypeID");
        PK_SiteID=getArguments().getString("PK_SiteID");
        PK_BlockID=getArguments().getString("PK_BlockID");
        PK_SectorID=getArguments().getString("PK_SectorID");
        PlotNumber=getArguments().getString("Plot Number");


        plotAvailabilityModels = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressDialog=new ProgressDialog(getContext());
getData();
        return view;
    }
    private void filter(String text) {
        ArrayList<PlotAvailabilityModel> filteredList = new ArrayList<>();
        for (PlotAvailabilityModel item : plotAvailabilityModels) {
            if (item.getTv_Plot_Area().toLowerCase().contains(text.toLowerCase())|| item.getTv_Plot_Block().toLowerCase().contains(text.toLowerCase())||item.getTv_plot_Number().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    public void getData()
    {
        String showUrl="http://test.abdolphininfratech.com/WebAPI/PlotAvailability";
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstPlot");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PlotAvailabilityModel payoutLedgerModal=new PlotAvailabilityModel(jsonObject1.getString("PlotID"),
                                jsonObject1.getString("PlotArea"),jsonObject1.getString("SectorName"),jsonObject1.getString("BlockName"));
                        plotAvailabilityModels.add(payoutLedgerModal);
                    }
                    adapter = new PlotAvailabilityAdopter(plotAvailabilityModels, getContext());
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
                parms.put("SiteID","2");
               parms.put("SectorID","2");
              parms.put("BlockID","2");
              parms.put("SiteTypeID","1");
              parms.put("PlotNumber","A1");
              //  parms.put("PlotNumber", PlotNumber);


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
