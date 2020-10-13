package com.example.abdolphininfratech.Fragement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SummaryReportSearchFragment extends Fragment {

    String title="Summary Report";
    EditText et_from_date,et_to_date,search_customer_id,search_customer_name,search_mobile_number,search_booking_number,search_plot_number;
    Spinner spinner_select_site,spinner_select_sector,spinner_select_block;
    Button btn_search;
    String PK_SectorID,PK_SiteID,PK_BlockID;
    private ArrayList<String> SelectSite,SelectSector,SelectBlock;
    private DatePickerDialog mDatePickerDialog,getmDatePickerDialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_report_search_fragment, container, false);


        et_from_date=view.findViewById(R.id.from_date);
        et_to_date=view.findViewById(R.id.to_date);

        search_customer_id=view.findViewById(R.id.search_customer_id);
        search_customer_name=view.findViewById(R.id.search_customer_name);
        search_mobile_number=view.findViewById(R.id.search_mobile_number);
        search_booking_number=view.findViewById(R.id.search_booking_number);
        search_plot_number=view.findViewById(R.id.search_plot_number);

        spinner_select_site=view.findViewById(R.id.spinner_select_site);
        spinner_select_sector=view.findViewById(R.id.spinner_select_sector);
        spinner_select_block=view.findViewById(R.id.spinner_select_block);

        SelectSite = new ArrayList<String>();
        SelectSector=new ArrayList<String>();
        SelectBlock=new ArrayList<String>();

        spinner_select_site.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
                if (i==1)
                {
                    PK_SiteID  = String.valueOf(i);

                    getSelectSector();
                }
                else
                {
                    Toast.makeText(getContext(), "Plz Select Site", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_select_sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==1)
                {
                    PK_SectorID = String.valueOf(i);


                    getSelectBlock();

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_select_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==1)
                {
                    String item=adapterView.getItemAtPosition(i).toString();
                    PK_BlockID= String.valueOf(i);

                }
                else
                {
                   // Toast.makeText(getContext(), "Plz Select Block", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        et_from_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog.show();
                return false;
            }
        });
        et_to_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                getmDatePickerDialog.show();
                return false;
            }
        });

        getSelectSite();

        setDateTimeField();
        setToDateTimeField();
        btn_search=view.findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getSearchData();
            }
        });

        return view;

    }
    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

                et_from_date.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private void setToDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        getmDatePickerDialog= new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();

                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String toDate = sd.format(startDate);

                et_to_date.setText(toDate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        getmDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    public void getSearchData()
    {

        String search_customerId=search_customer_id.getText().toString().trim();
        String search_customerName=search_customer_name.getText().toString().trim();
        String search_mobileNumber=search_mobile_number.getText().toString().trim();
        String search_bookingNumber=search_booking_number.getText().toString().trim();
        String search_plotNumber=search_plot_number.getText().toString().trim();
        String From_Date=et_from_date.getText().toString().trim();
        String To_Date=et_to_date.getText().toString().trim();

        Fragment mFragment = null;
        Bundle bundle=new Bundle();
        bundle.putString("Search_CustomerID",search_customerId);
        bundle.putString("Search_CustomerName",search_customerName);
        bundle.putString("Search_MobileNumber",search_mobileNumber);
        bundle.putString("Search_BookingNumber",search_bookingNumber);
        bundle.putString("Search_PlotNumber",search_plotNumber);

        bundle.putString("From_Date",From_Date);
        bundle.putString("To_Date",To_Date);
        bundle.putString("PK_SiteID",PK_SiteID);
        bundle.putString("PK_SectorID",PK_SectorID);
        bundle.putString("PK_BlockID",PK_BlockID);
        mFragment = new GetSummaryReportFragment();
        mFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,mFragment).addToBackStack("login").commit();

    }

    public void getSelectSite()
    {
        final String SelelectSiteUrl="http://test.abdolphininfratech.com/WebAPI/GetSite";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, SelelectSiteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstsite");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PK_SiteID = jsonObject1.getString("PK_SiteID");
                        String SiteName = jsonObject1.getString("SiteName");
                        SelectSite.add(SiteName);
                    }
                    spinner_select_site.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,SelectSite));

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
    public void getSelectSector()
    {
        String SelectSectorUrl="http://test.abdolphininfratech.com/WebAPI/GetSector";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, SelectSectorUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstsite");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PK_SectorID = jsonObject1.getString("PK_SectorID");
                        String SectorName = jsonObject1.getString("SectorName");
                        SelectSector.add(SectorName);
                    }
                    spinner_select_sector.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,SelectSector));

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

    public void getSelectBlock()
    {
        final String SelectBlockUrl="http://test.abdolphininfratech.com/WebAPI/GetBlock";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, SelectBlockUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstBlock");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PK_BlockID = jsonObject1.getString("PK_BlockID");
                        String BlockName = jsonObject1.getString("BlockName");
                        SelectBlock.add(BlockName);
                    }
                    spinner_select_block.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,SelectBlock));


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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

}
