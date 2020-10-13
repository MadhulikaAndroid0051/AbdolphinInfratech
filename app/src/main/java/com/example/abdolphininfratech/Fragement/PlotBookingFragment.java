package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
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
import com.example.abdolphininfratech.Adopter.PlotBookingAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.PlotBookingModel;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlotBookingFragment extends Fragment {

    private List<PlotBookingModel> plotBookingModels;
    private PlotBookingAdopter adapter;
    RecyclerView recyclerView;
    Spinner et_selectsite,et_select_sector;
    SearchView searchView;
    private ArrayList<String> getSite;
    ProgressDialog progressDialog;
    String search_customerId,search_affiliateId,search_bookingId,plot_number,from_date,to_date,select_site,select_block,select_sector;
    Button search_bar;
    String LoginId;
    String title="Booking Details";

    EditText editTextSearch;
    //private PlotBookingModelView plotBookingModelView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plotbooking_fragment, container, false);
        editTextSearch=view.findViewById(R.id.search);
        LoginId = SessionHandler.getInstance().get(getContext(), PerferencesUtility.LoginID);
       // Toast.makeText(getContext(),LoginId+ "", Toast.LENGTH_SHORT).show();
        search_customerId=getArguments().getString("Search_CustomerID");
        search_affiliateId=getArguments().getString("Search_AffiliateId");
         search_bookingId=getArguments().getString("Search_BookingId");
         plot_number=getArguments().getString("Search_PlotNumber");
         from_date=getArguments().getString("From_Date");
         to_date=getArguments().getString("To_Date");
         select_site=getArguments().getString("PK_SiteID");
         select_block=getArguments().getString("PK_SectorID");
         select_sector=getArguments().getString("PK_BlockID");
        //Toast.makeText(getContext(), search_bookingId+"", Toast.LENGTH_SHORT).show();

        progressDialog=new ProgressDialog(getContext());
        getData();
        //Select_Sector();
        getSite = new ArrayList<>();
        search_bar = view.findViewById(R.id.search_bar);
        recyclerView = view.findViewById(R.id.recyclerview_plotbooking);
        plotBookingModels = new ArrayList<>();
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


        return view;

    }
    private void filter(String text) {
        ArrayList<PlotBookingModel> filteredList = new ArrayList<>();
        for (PlotBookingModel item : plotBookingModels) {
            if (item.getBooking_date().toLowerCase().contains(text.toLowerCase())||
                    item.getBranch_name().toLowerCase().contains(text.toLowerCase())||
                    item.getNet_amnt().toLowerCase().contains(text.toLowerCase())||item.getAffiliate_details().contains(text.toLowerCase())||item.getCustomer_details()
                    .toLowerCase().contains(text.toLowerCase())||item.getPayment_plan().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    public void getData()
    {
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        final String showUrl="http://test.abdolphininfratech.com/WebAPI/BookingList?LoginId="+LoginId+"&PK_BookingId="+search_customerId+
                "&BookingNumber="+search_bookingId+"";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstbooking");
                    //Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PlotBookingModel plotBookingModel=new PlotBookingModel(jsonObject1.getString("BranchName"),
                                jsonObject1.getString("BookingNumber"),jsonObject1.getString("BookingDate"),jsonObject1.getString("PlotNumber"),
                                jsonObject1.getString("PaymentPlanID"),jsonObject1.getString("CustomerLoginID"),jsonObject1.getString("AssociateLoginID"),
                                jsonObject1.getString("PlotAmount"),jsonObject1.getString("PK_PLCCharge"),
                                jsonObject1.getString("NetPlotAmount"),jsonObject1.getString("PaidAmount"));
                        plotBookingModels.add(plotBookingModel);
                    }
                    adapter = new PlotBookingAdopter(plotBookingModels, getContext());
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
        })/*{
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
              parms.put("PK_BookingId",search_customerId);
              parms.put("LoginId",search_affiliateId);
               parms.put("BookingNumber", search_bookingId);
            // parms.put("SiteID",select_site);
            // parms.put("SectorID",select_sector);
          // parms.put("BlockID",select_block);
               parms.put("PlotNumber",plot_number);
            //   parms.put("BookingNumber",search_bookingId);
             //  parms.put("FromDate",from_date);
             //   parms.put("ToDate",to_date);

                return parms;

            }
        }*/;
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

}
