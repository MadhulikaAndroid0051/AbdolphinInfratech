package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.abdolphininfratech.Adopter.PayoutLedgerAdopter;
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

public class PayoutLedgerFragment extends Fragment {
    private List<PayoutLedgerModal> payoutLedgerModals;
    private PayoutLedgerAdopter adapter;
    RecyclerView recyclerView;
    SearchView searchView;
    EditText editTextSearch;
    String UserId;
     String title="PayOut Ledger";
    ProgressDialog progressDialog;
    final String showUrl="http://test.abdolphininfratech.com//WebAPI/PayoutLedger";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payoutedger_fragment, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);
        UserId = SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);

        progressDialog=new ProgressDialog(getContext());
        payoutLedgerModals = new ArrayList<>();
        //  recyclerView.setAdapter(adapter);
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
        ArrayList<PayoutLedgerModal> filteredList = new ArrayList<>();
        for (PayoutLedgerModal item : payoutLedgerModals) {
            if (item.getNarration().toLowerCase().contains(text.toLowerCase())|| item.getCreditAmount().toLowerCase().contains(text.toLowerCase())||item.getDebit().toLowerCase().contains(text.toLowerCase())) {
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
                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lstpayoutledger");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        PayoutLedgerModal payoutLedgerModal=new PayoutLedgerModal(jsonObject1.getString("TransactionDate"),
                                jsonObject1.getString("Narration"),jsonObject1.
                                getString("Credit"),jsonObject1.getString("Debit"));
                               payoutLedgerModals.add(payoutLedgerModal);
                    }
                    adapter=new PayoutLedgerAdopter(payoutLedgerModals,getContext());
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
                parms.put("UserID",UserId);
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
