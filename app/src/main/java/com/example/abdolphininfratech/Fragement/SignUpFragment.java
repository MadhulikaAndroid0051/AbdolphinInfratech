package com.example.abdolphininfratech.Fragement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {
    TextView rg_login;
    Spinner sp_DesignationID,sp_BranchId;
    ArrayList<String> designationid;
    ArrayList<String> branchid;
    ProgressDialog progressDialog;
    String desiganation_id,branch_id;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String designationUrl="http://test.abdolphininfratech.com/WebAPI/GetDesignation";
    String branchURl="http://test.abdolphininfratech.com/WebAPI/GetBranch";
    Button btn_reg;
    EditText et_FirstName,et_LastName,et_Contact,et_Email,et_PinCode,et_Address,et_PanNo;
    String first,last,contact,email,pincode,Phanno,address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);

        sp_DesignationID=view.findViewById(R.id.DesignationID);
        sp_BranchId=view.findViewById(R.id.BranchID);
        et_LastName=view.findViewById(R.id.et_lastname);
        et_FirstName=view.findViewById(R.id.et_firstname);
        et_Contact=view.findViewById(R.id.et_contact);
        et_Email=view.findViewById(R.id.et_email);
        et_PinCode=view.findViewById(R.id.et_pinCode);
        et_Address=view.findViewById(R.id.et_Address);
        et_PanNo=view.findViewById(R.id.et_panno);
          btn_reg=view.findViewById(R.id.btn_reg);
        rg_login=view.findViewById(R.id.reg_login);
        progressDialog=new ProgressDialog(getContext());
        designationid=new ArrayList<>();
        branchid=new ArrayList<>();
        getDesignarionId();
        getBranchId();

        rg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = null;
                mFragment = new LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearlayout, mFragment).addToBackStack("login").commit();

            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = et_FirstName.getText().toString().trim();
                last = et_LastName.getText().toString().trim();
                contact=et_Contact.getText().toString().trim();
                email=et_Email.getText().toString().trim();
                pincode=et_PinCode.getText().toString().trim();
                address=et_Address.getText().toString().trim();
                Phanno=et_PanNo.getText().toString().trim();

               /* if (et_FirstName.length() > 0 && et_LastName.length() > 0 && et_Contact.length()>0 && et_Email.length()>0 && et_PinCode.length()>0 && et_Address.length()>0 && et_PanNo.length()>0) {

                    getSignUp();
                     }
                else
                {
                    Toast.makeText(getContext(), "Fill the value", Toast.LENGTH_SHORT).show();
                }*/
               if (et_FirstName.length()==0)
               {
                   et_FirstName.setError("Plz Enter First Name");
               }
               if (et_LastName.length()==0)
               {
                   et_LastName.setError("Plz Enter Last Name");
               }
               if (et_Email.length()==0)
               {
                  et_Email.setError("Plz Enter Validate Email");
               }
              else if (!Patterns.EMAIL_ADDRESS.matcher(et_Email.getText().toString()).matches())
               {
                   et_Email.setError("Plz Enter Validate Email");
               }
              else if (!et_Contact.getText().toString().matches("[0-9]{10}"))
               {
                   et_Contact.setError("Enter Only 10 digit Mobile Number");
               }
              else if (!et_PinCode.getText().toString().matches("[0-9]{6}"))
               {
                   et_PinCode.setError("Enter Only 6 digit Pin Code");
               }
              else if (et_Address.length()==0)
               {
                  et_Address.setError("Enter Address");
               }
              else if (!et_PanNo.getText().toString().matches("[0-9]{10}"))
               {
                   et_PanNo.setError("Enter only 10 digit Pan Number");
               }
              else
               {
                   getSignUp();
               }
            }
        });
       sp_DesignationID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            if (position>=0)
            {
                desiganation_id=String.valueOf(position);
                Toast.makeText(getContext(),desiganation_id+ "", Toast.LENGTH_SHORT).show();
            }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        sp_BranchId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            if (position>=0)
            {
              branch_id=String.valueOf(position);
                Toast.makeText(getContext(),branch_id+ "", Toast.LENGTH_SHORT).show();
            }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });



        return view;
    }

    public void getDesignarionId()
    {

        StringRequest stringRequest1=new StringRequest(Request.Method.GET, designationUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String Status=jsonObject.getString("Status");
                    if (Status.equalsIgnoreCase("0")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("lstdesign");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String DesignationName = jsonObject1.getString("DesignationName");
                            String designation_id=jsonObject1.getString("PK_DesignationID");
                            designationid.add(DesignationName);
                        }
                    }
                 sp_DesignationID  .setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,designationid));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest1);
    }
    public void getBranchId()
    {
        StringRequest stringRequest2=new StringRequest(Request.Method.GET, branchURl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Status = jsonObject.getString("Status");
                    if (Status.equalsIgnoreCase("0")) {
                        String branchname= jsonObject.getString("BranchName");
                        String branch_id=jsonObject.getString("PK_BranchID");
                        branchid.add(branchname);
                    }

                    sp_BranchId  .setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,branchid));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest2);

    }
    public void getSignUp()
    {

        progressDialog.show();
        String loginUrl="http://test.abdolphininfratech.com/WebAPI/AssociateSignup";
        StringRequest stringRequest=new StringRequest(Request.Method.POST,loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    final String  status=jsonObject.getString("Status");
                    final String message=jsonObject.getString("SuccessMessage");
                    final String errormessage=jsonObject.getString("ErrorMessage");
                    Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT).show();


                    if (status.equalsIgnoreCase("0")&& message.equalsIgnoreCase("Registration Successful !"))
                    {
                       // SaveSharedPreferences.setLoggedIn(getContext(),true);
                        Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT).show();

                       /* Intent I=new Intent(getContext(), DashboardActivity.class);
                        I.setFlags(I.FLAG_ACTIVITY_CLEAR_TASK | I.FLAG_ACTIVITY_CLEAR_TOP | I.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(I);
                        getActivity().finish();*/
                        nextButton();
                        String username=jsonObject.getString("LoginId");
                        String password=jsonObject.getString("Password");
                    }
                    else
                    {
                        Toast.makeText(getContext(),errormessage+"", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
               parms.put("BranchID",branch_id);
               parms.put("UserID","13");
                parms.put("DesignationID",desiganation_id);
                parms.put("FirstName",first);
                parms.put("AddedBy","1");
                parms.put("LastName",last);
                parms.put("Contact",contact);
                parms.put("PinCode",pincode);
                parms.put("Address",address);
                parms.put("PanNo",Phanno);
                parms.put("Email",email);

                return parms;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void nextButton() {
        Fragment mFragment = null;
        mFragment = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).addToBackStack("login").commit();

    }
    }



