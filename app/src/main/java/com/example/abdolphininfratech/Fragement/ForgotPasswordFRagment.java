package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordFRagment extends Fragment {

    EditText et_username, et_mobile_number;
    Button btn_forgot_password;
    ProgressDialog progressDialog;
    String Url="http://test.abdolphininfratech.com/WebAPI/ForgetPass?LoginID=ADOL660845&Contact=8808098724";
    String title="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forgotpassword_fragment, container, false);

        et_username = view.findViewById(R.id.et_username);
        et_mobile_number = view.findViewById(R.id.et_mobile_number);
        btn_forgot_password = view.findViewById(R.id.btn_forgot_password);
        progressDialog=new ProgressDialog(getContext());
        btn_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_username.length() > 0 && et_mobile_number.length() > 0) {
                    final String user=et_username.getText().toString().trim();
                    final String mobile=et_mobile_number.getText().toString().trim();
                    GetForgotPassword(user,mobile);
                    Fragment mFragment = null;
                    mFragment = new LoginFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).commit();


                }
                else
                {
                    Toast.makeText(getContext(), "Fill value", Toast.LENGTH_SHORT).show();
                }

            }
        });



        return view;
    }


    public void GetForgotPassword(final String user, final String mobile){
        progressDialog.show();
        progressDialog.setMessage("Please Wait");

        String Url="http://test.abdolphininfratech.com/WebAPI/ForgetPass?LoginID="+user+"&Contact="+mobile+"";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String Status=jsonObject.getString("Status");
                    String Message=jsonObject.getString("SuccessMessage");
                    String ErrorMessage=jsonObject.getString("ErrorMessage");
                    if (Status.equalsIgnoreCase("0"))
                    {
                        Toast.makeText(getContext(),Message+ "", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(),ErrorMessage+ "", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
               progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                  progressDialog.dismiss();
                Toast.makeText(getContext(), "Network Problem", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }

    }
