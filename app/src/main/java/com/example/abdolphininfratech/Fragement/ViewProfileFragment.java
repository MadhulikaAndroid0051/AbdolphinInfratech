package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdolphininfratech.Auth.AppConstent;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.SaveSharedPreferences;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ViewProfileFragment extends Fragment {

    private String title = "View Profile";

    public ViewProfileFragment() {
        // Required empty public constructor
    }


    TextView tv_sponsorid,tv_SponsorName,tv_firstname,tv_lastname,tv_designationid,tv_DesignationName,tv_BranchID,
            tv_Contact,tv_Email,tv_State,tv_City,tv_Address,tv_Pincode,tv_PanNo,tv_branchname,tv_AdharNumber,tv_bankAccountNo,
            tv_BankName,tv_bankbranch,tv_IFSCCode;

    String UserID="7";
    String sponsorid,SponsorName,firstname,lastname,designationid,DesignationName,BranchID,Contact,Email,State,
            City,Address,Pincode,PanNo,branchname,AdharNumber,bankAccountNo,BankName,bankbranch,IFSCCode;

    String ViewProfile="http://test.abdolphininfratech.com//WebAPI/ViewProfile";
      String LoginId;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewprofile_fragment, container, false);
        LoginId= SessionHandler.getInstance().get(getContext(),PerferencesUtility.LoginID);


       // Toast.makeText(getContext(),LoginId+ "", Toast.LENGTH_SHORT).show();
        progressDialog=new ProgressDialog(getContext());
        tv_sponsorid=view.findViewById(R.id.tv_sponsorid);

        tv_firstname=view.findViewById(R.id.tv_firstname);

        tv_lastname=view.findViewById(R.id.tv_lastname);
        tv_designationid=view.findViewById(R.id.tv_designationid);
        tv_DesignationName=view.findViewById(R.id.tv_DesignationName);
        tv_BranchID=view.findViewById(R.id.tv_BranchID);
        tv_Contact=view.findViewById(R.id.tv_Contact);
        tv_Email=view.findViewById(R.id.tv_Email);
        tv_State=view.findViewById(R.id.tv_State);
        tv_City=view.findViewById(R.id.tv_City);
        tv_Address=view.findViewById(R.id.tv_Address);
        tv_Pincode=view.findViewById(R.id.tv_Pincode);
        tv_PanNo=view.findViewById(R.id.tv_PanNo);
        tv_branchname=view.findViewById(R.id.tv_branchname);
        tv_AdharNumber=view.findViewById(R.id.tv_AdharNumber);
        tv_bankAccountNo=view.findViewById(R.id.tv_bankAccountNo);
        tv_BankName=view.findViewById(R.id.tv_BankName);
        tv_bankbranch=view.findViewById(R.id.tv_bankbranch);
        tv_IFSCCode=view.findViewById(R.id.tv_IFSCCode);
        ViewProfile();
        return view;

    }
    public void ViewProfile()
    {
        progressDialog.show();
        progressDialog.setMessage("Loading..");
        StringRequest stringRequest=new StringRequest(Request.Method.GET,ViewProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                  //  Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT).show();
                    sponsorid=jsonObject.getString("SponsorID");
                    tv_sponsorid.setText(sponsorid);
                     firstname=jsonObject.getString("FirstName");
                    tv_firstname.setText(firstname);
                    lastname=jsonObject.getString("LastName");
                    tv_lastname.setText(lastname);
                    designationid=jsonObject.getString("DesignationID");
                    tv_designationid.setText(designationid);
                    DesignationName=jsonObject.getString("DesignationName");
                    tv_DesignationName.setText(DesignationName);
                    BranchID=jsonObject.getString("BranchID");
                    tv_BranchID.setText(BranchID);
                    Contact=jsonObject.getString("Contact");
                    tv_Contact.setText(Contact);
                    Email=jsonObject.getString("Email");
                    tv_Email.setText(Email);
                    State=jsonObject.getString("State");
                    tv_State.setText(State);
                    City=jsonObject.getString("City");
                    tv_City.setText(City);
                    Address=jsonObject.getString("Address");
                    tv_Address.setText(Address);
                    Pincode=jsonObject.getString("Pincode");
                    tv_Pincode.setText(Pincode);
                    PanNo=jsonObject.getString("PanNo");
                    tv_PanNo.setText(PanNo);
                    branchname=jsonObject.getString("BranchName");
                    tv_branchname.setText(branchname);
                    AdharNumber=jsonObject.getString("AdharNumber");
                    tv_AdharNumber.setText(AdharNumber);
                    bankAccountNo=jsonObject.getString("BankAccountNo");
                    tv_bankAccountNo.setText(bankAccountNo);
                    BankName=jsonObject.getString("BankName");
                    tv_BankName.setText(BankName);
                    bankbranch=jsonObject.getString("BankBranch");
                    tv_bankbranch.setText(bankbranch);
                    IFSCCode=jsonObject.getString("IFSCCode");
                    tv_IFSCCode.setText(IFSCCode);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue =Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(title);

    }
}
