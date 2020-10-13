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
import com.example.abdolphininfratech.Adopter.AssociateDownlineDetailAdopter;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.ModelClass.AssociateDownlineDetailModel;
import com.example.abdolphininfratech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssociateDownlineDetailFragment extends Fragment {

    private List<AssociateDownlineDetailModel> associateDownlineDetailModelList;
    private AssociateDownlineDetailAdopter adapter;
    RecyclerView recyclerView;
    SearchView searchView;
    ProgressDialog progressDialog;
    EditText editTextSearch;
    String LoginId;
   String title="Affiliate Downline";
    final String showUrl="http://test.abdolphininfratech.com/WebAPI/AssociateDownlineDetail";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.associatedownlinedetail_fragment, container, false);
        editTextSearch=view.findViewById(R.id.search);

        LoginId= SessionHandler.getInstance().get(getContext(), PerferencesUtility.LoginID);

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


        //searchView=view.findViewById(R.id.search_view1);
      /*  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);

                return false;
            }
        });*/

        recyclerView=view.findViewById(R.id.recyclerview1);

        progressDialog=new ProgressDialog(getContext());
        associateDownlineDetailModelList = new ArrayList<>();
        //  recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
              @Override
              public boolean onQueryTextSubmit(String s) {
                  return true;
              }

              @Override
              public boolean onQueryTextChange(String s) {
                  return false;
              }
          });*/


        getData();
        return view;
    }
    private void filter(String text) {
        ArrayList<AssociateDownlineDetailModel> filteredList = new ArrayList<>();
        for (AssociateDownlineDetailModel item : associateDownlineDetailModelList) {
            if (item.getAffiliateID().toLowerCase().contains(text.toLowerCase())|| item.getAffiliateName().toLowerCase().contains(text.toLowerCase())
                    ||item.getContact().toLowerCase().contains(text.toLowerCase())|| item.getRank().toLowerCase().contains(text.toLowerCase())||item.getRankName().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
    public void getData()
    {
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstdownline");
                    //Toast.makeText(getContext(),response+ "", Toast.LENGTH_SHORT).show();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        AssociateDownlineDetailModel associateDownlineDetailModel=new AssociateDownlineDetailModel(jsonObject1.getString("AssociateID"),
                                jsonObject1.getString("DesignationName"),jsonObject1.getString("Percentage"),
                                jsonObject1.getString("BranchName"),
                                jsonObject1.getString("Contact"));
                        associateDownlineDetailModelList.add(associateDownlineDetailModel);
                    }
                    adapter = new AssociateDownlineDetailAdopter(associateDownlineDetailModelList, getContext());
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
                Toast.makeText(getContext(),error+ "Server Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("LoginId",LoginId);
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
