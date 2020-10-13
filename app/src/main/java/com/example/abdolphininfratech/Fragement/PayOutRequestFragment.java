package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PayOutRequestFragment extends Fragment {


    //String ViewAmountUrl="http://test.abdolphininfratech.com//WebAPI/PayoutRequest?UserID=7";
    String ViewAmountUrl="http://test.abdolphininfratech.com//WebAPI/PayoutRequest";

    TextView tv_amount;
    EditText et_balance;
    String UserID;
    ProgressDialog progressDialog;
    String title="PayOut Request";




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payoutrequest_fragment, container, false);

        UserID = SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);

        tv_amount=view.findViewById(R.id.tv_amount);
        et_balance=view.findViewById(R.id.et_balance);
        progressDialog=new ProgressDialog(getContext());
        ViewBalance();

        return view;
    }
    public void ViewBalance()
    {
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ViewAmountUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String Status=jsonObject.getString("Status");

                    if (Status.equalsIgnoreCase("0")){
                        String viewamount = jsonObject.getString("Balance");

                        tv_amount.setText(viewamount);
                    }
                    else
                    {
                       // Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
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