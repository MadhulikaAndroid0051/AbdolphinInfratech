package com.example.abdolphininfratech;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdolphininfratech.Adopter.ConsultDoctorAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.Lstassociate;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class HomeFragment extends Fragment {
    // private HomeViewModel homeViewModel;
    TextView et_headline, et_viewnews;
    String AssociateId;
    String LoginID;
    TextView Total_Associates, Total_Active_Id, Total_Business, Self_Business, Team_Business, Total_Booking, Self_Booking, Total_Registry, Self_Registry, Team_Registry, Team_Booking;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Lstassociate> movieList;
    private ConsultDoctorAdopter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    final String showlistUrl = "http://test.abdolphininfratech.com/WebAPI/GetDueInstallmentForAssociateDashBoard";

    String Url = "http://test.abdolphininfratech.com/WebAPI/GetTotalForAssociateDashBoard?AssociateID=";
    String NewsUrl = "http://test.abdolphininfratech.com/WebAPI/GetNewsDetailsForAssociateDashBoard?AssociateID=";

    //PAi chart
    String a, b, c;
    //PieChart pieChart;
    TextView summary_report;

    PieChart mChart;
    // we're going to display pie chart for school attendance
    private int[] yValues =new int[4];
    private String[] xValues = new String[4];
    //,
//
    // colors for different sections in pieChart
    public static  final int[] MY_COLORS = {
            Color.rgb(78,105,226),  Color.rgb(255,0,0), Color.rgb(219,60,55), Color.rgb(0,101,0)
    };

    public HomeFragment() {
        // Required empty public constructor
    }
 String title="Home";

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        Total_Active_Id = view.findViewById(R.id.Total_Active_Id);
        Total_Associates = view.findViewById(R.id.Total_Associates);
        Total_Business = view.findViewById(R.id.Total_Business);
        Self_Business = view.findViewById(R.id.Self_Business);
        Team_Business = view.findViewById(R.id.Team_Business);
        Total_Booking = view.findViewById(R.id.Total_Booking);
        Self_Booking = view.findViewById(R.id.Self_Booking);
        Total_Registry = view.findViewById(R.id.Total_Registry);
        Self_Registry = view.findViewById(R.id.Self_Registry);
        Team_Registry = view.findViewById(R.id.Team_Registry);
        Team_Booking = view.findViewById(R.id.Team_Booking);
        et_headline = view.findViewById(R.id.et_headline);
        et_viewnews = view.findViewById(R.id.view_news);

        summary_report = view.findViewById(R.id.summary_report);

        AssociateId= SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);

        progressDialog = new ProgressDialog(getContext());
        movieList = new ArrayList<>();
        //  recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AssociateId= SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);
        LoginID = SessionHandler.getInstance().get(getContext(), PerferencesUtility.LoginID);

        ShowData();
        //showListData();
        getData();
        getNews();
        mChart =view.findViewById(R.id.chart1);
        loadList();
        //   mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setDrawHoleEnabled(false);
        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(getContext(),
                        xValues[e.getXIndex()] + " is " + e.getVal() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
    }

    public void ShowData() {
        String Url1=Url+AssociateId;
        progressDialog.show();
        progressDialog.setMessage("Loading....");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    progressDialog.dismiss();

                    JSONArray array = jsonObject.getJSONArray("lstassociate");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        // JS lstassociate=jsonObject1.J
                        String Total_Associates1 = jsonObject1.getString("TotalAssociate");
                        Total_Associates.setText(Total_Associates1);

                        String Total_Active_Id1 = jsonObject1.getString("TotalActiveId");
                        Total_Active_Id.setText(Total_Active_Id1);
                        String Total_Business1 = jsonObject1.getString("TotalBusiness");
                        Total_Business.setText(Total_Business1);
                        String Self_Business1 = jsonObject1.getString("SelfBusiness");
                        Self_Business.setText(Self_Business1);
                        String Team_Business1 = jsonObject1.getString("TeamBusiness");
                        Team_Business.setText(Team_Business1);
                        String Total_Booking1 = jsonObject1.getString("TotalBooking");
                        Total_Booking.setText(Total_Booking1);
                        String TeemBookinf = jsonObject1.getString("TeamBooking");
                        Team_Booking.setText(TeemBookinf);
                        String SelfBooking1 = jsonObject1.getString("SelfBooking");
                        Self_Booking.setText(SelfBooking1);
                        String Total_Registry1 = jsonObject1.getString("Totalregistry");
                        Total_Registry.setText(Total_Registry1);
                        String Self_Registr1 = jsonObject1.getString("SelfRegistry");
                        Self_Registry.setText(Self_Registr1);
                        String Team_Registry1 = jsonObject1.getString("TeamRegistry");
                        Team_Registry.setText(Team_Registry1);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error + "", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    public void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, showlistUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("lstdueinstallment");
                    // Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Lstassociate lstassociate = new Lstassociate(jsonObject1.getString("CustomerID"), jsonObject1.getString("CustomerName")
                                , jsonObject1.getString("PlotNumber"), jsonObject1.getString("InstallmentNo"), jsonObject1.getString("InstallmentAmount"));
                        movieList.add(lstassociate);


                    }

                    adapter = new ConsultDoctorAdopter(movieList, getContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                linearLayoutManager = new LinearLayoutManager(getContext());

                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error + "", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                //parms.put("bookingid","");
                parms.put("AssociateID", AssociateId);
                return parms;
            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    public void getNews() {
        String News=NewsUrl+AssociateId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, News, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("lstnewsdetail");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String headline = jsonObject1.getString("NewsHeading");
                        et_headline.setText(headline);
                        String viewnews = jsonObject1.getString("NewsBody");
                        et_viewnews.setText(viewnews);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Check Internet Connection ", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void setDataForPieChart() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yValues.length; i++)
            yVals1.add(new Entry(yValues[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xValues.length; i++)
            xVals.add(xValues[i]);

        // create pieDataSet
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(4);
        dataSet.setSelectionShift(4);

        // adding colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        // Added My Own colors
        for (int c : MY_COLORS)
            colors.add(c);


        dataSet.setColors(colors);

        //  create pie data object and set xValues and yValues and set it to the pieChart
        PieData data = new PieData(xVals, dataSet);
        //   data.setValueFormatter(new DefaultValueFormatter());
        //   data.setValueFormatter(new PercentFormatter());

        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // refresh/update pie chart
        mChart.invalidate();

        // animate piechart
        mChart.animateXY(1400, 1400);


        // Legends to show on bottom of the graph
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }


    public class MyValueFormatter implements ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0"); // use one decimal if needed
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return mFormat.format(value) + ""; // e.g. append a dollar-sign
        }

    }

    private void loadList() {
        //getting the progressbar
        String url = "http://test.abdolphininfratech.com/WebAPI/GetGraphDetailsOfPlot?LoginId="+LoginID;
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        progressDialog.show();
        //making the progressbar visible


        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressDialog.dismiss();


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("lstdata");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);
                                String t=heroObject.get("Title").toString();
                                JSONArray jsonArray=heroObject.getJSONArray("lstgraphdetails");
                                for(int j=0;j<jsonArray.length();j++)
                                {
                                    yValues= new int[]{Integer.parseInt(jsonArray.getJSONObject(0).get("Total").toString()),
                                            Integer.parseInt(jsonArray.getJSONObject(1).get("Total").toString()),
                                            Integer.parseInt(jsonArray.getJSONObject(2).get("Total").toString()),Integer.parseInt(jsonArray.getJSONObject(3).get("Total").toString())};
                                    xValues= new String[]{jsonArray.getJSONObject(0).get("Status").toString(),
                                            jsonArray.getJSONObject(1).get("Status").toString(),
                                            jsonArray.getJSONObject(2).get("Status").toString()
                                            ,jsonArray.getJSONObject(3).get("Status").toString()};

                                }
                                setDataForPieChart();
                            }

                            //creating custom adapter object
                          /*  ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

}

