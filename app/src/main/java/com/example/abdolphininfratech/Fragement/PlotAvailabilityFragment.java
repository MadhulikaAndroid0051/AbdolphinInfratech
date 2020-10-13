package com.example.abdolphininfratech.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlotAvailabilityFragment extends Fragment {
    String SelectTypeUrl="http://test.abdolphininfratech.com/WebAPI/GetSiteType";
    String SelectSiteUrl="http://test.abdolphininfratech.com/WebAPI/GetSite";
    String GetBlockUrl="http://test.abdolphininfratech.com/WebAPI/GetBlock";
    String GetSectorURl="http://test.abdolphininfratech.com/WebAPI/GetSector";

    Spinner site_type,select_site,select_sector,select_block;
    String PK_BlockID,PK_SiteTypeID,PK_SectorID,PK_SiteID;
    ArrayList<String> SiteTypeList,SelectSite,SelectSector,GetSectorList;
     EditText saerch_plot_number;
    Button btn_search;
    String title="Plot Availability";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plotvvailability_fragment, container, false);


        saerch_plot_number=view.findViewById(R.id.saerch_plot_number);

        site_type=view.findViewById(R.id.site_type);
        select_site=view.findViewById(R.id.select_site);
        select_sector=view.findViewById(R.id.select_sector);
        select_block=view.findViewById(R.id.select_block);
        btn_search=view.findViewById(R.id.btn_search);
        SiteTypeList=new ArrayList<>();
        SelectSite=new ArrayList<>();
        SelectSector=new ArrayList<>();
        GetSectorList=new ArrayList<>();



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SendData();
            }
        });

        site_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==1)
                {
                  getSelectSite();
                    PK_SiteTypeID = String.valueOf(i);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        select_site.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              if (i==1)
              {
                  PK_SiteID = String.valueOf(i);

                  GetSector();

              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        select_sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==1)
                {
                    setGetBlockUrl();
                    PK_SectorID=String.valueOf(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getSiteType();
        return view;
    }
    public void getSiteType()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, SelectTypeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstsitetype");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String SiteTypeName=jsonObject1.getString("SiteTypeName");
                        PK_SiteTypeID=jsonObject1.getString("PK_SiteTypeID");
                        SiteTypeList.add(SiteTypeName);
                    }
                     site_type.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,SiteTypeList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void getSelectSite()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, SelectSiteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstsite");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PK_SiteID=jsonObject1.getString("PK_SiteID");
                        String SiteName=jsonObject1.getString("SiteName");
                        SelectSite.add(SiteName);
                    }

                    select_site.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,SelectSite));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void setGetBlockUrl()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, GetBlockUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstBlock");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                         PK_BlockID=jsonObject1.getString("PK_BlockID");
                        String BlockName=jsonObject1.getString("BlockName");
                        SelectSector.add(BlockName);

                    }
                    select_block.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,SelectSector));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void GetSector()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,GetSectorURl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstsite");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        PK_SectorID=jsonObject1.getString("PK_SectorID");
                        String BlockName=jsonObject1.getString("SectorName");
                        GetSectorList.add(BlockName);

                    }
                    select_sector.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,GetSectorList));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
    public void SendData()
    {
        String saerch_plot_number1=saerch_plot_number.getText().toString().trim();
        Bundle bundle=new Bundle();
        bundle.putString("PK_SiteTypeID",PK_SiteTypeID);
        bundle.putString("PK_SiteID",PK_SiteID);
        bundle.putString("PK_SectorID",PK_SectorID);
        bundle.putString("PK_BlockID",PK_BlockID);
        bundle.putString("Plot Number",saerch_plot_number1);

        Fragment mFragment = null;
        mFragment = new PlotAvailabilityDetailsFragment();
        mFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,mFragment).addToBackStack("login").commit();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

}
