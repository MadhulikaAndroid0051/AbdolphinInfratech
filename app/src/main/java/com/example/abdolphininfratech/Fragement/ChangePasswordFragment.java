package com.example.abdolphininfratech.Fragement;

import android.content.Intent;
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
import com.example.abdolphininfratech.MainActivity;
import com.example.abdolphininfratech.MainActivity2;
import com.example.abdolphininfratech.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordFragment extends Fragment {

    EditText et_oldpassword,et_newpassword,et_cunfpassword;
    Button changepassword;
    String oldpassword,newpassword,cunfpassword;
    String Pk_userId;
    String title="Change Password";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.changepassword_fragment, container, false);
        Pk_userId = SessionHandler.getInstance().get(getContext(), PerferencesUtility.Pk_userId);


        et_oldpassword=view.findViewById(R.id.et_currentpassword);
        et_newpassword=view.findViewById(R.id.et_newpassword);
        et_cunfpassword=view.findViewById(R.id.et_cnfpassword);
        changepassword=view.findViewById(R.id.btn_changepassword);


        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    oldpassword = et_oldpassword.getText().toString().trim();
                    newpassword = et_newpassword.getText().toString().trim();
                    cunfpassword = et_cunfpassword.getText().toString().trim();

               if (newpassword.equals(cunfpassword)) {

                    CunPassword();
                }
                else
                {
                    Toast.makeText(getContext(), "plz check match cunf password", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    public void CunPassword()
    {
       final  String ForgotPassword="http://test.abdolphininfratech.com/WebAPI/ChangePassword";
        StringRequest stringRequest=new StringRequest(Request.Method.POST,ForgotPassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    final String  status=jsonObject.getString("Status");
                    final String message=jsonObject.getString("SuccessMessage");
                    final String errormessage=jsonObject.getString("ErrorMessage");

                    if (status.equalsIgnoreCase("0") && message.equalsIgnoreCase("Password Changed Successfuly."))
                    {
                        Toast.makeText(getContext(), message+"", Toast.LENGTH_SHORT).show();
                        Intent I=new Intent(getContext(), MainActivity2.class);
                        startActivity(I);

                    }
                    else if (status.equalsIgnoreCase("1") && errormessage.equalsIgnoreCase("Old Password is not correct.."))
                    {
                        Toast.makeText(getContext(), errormessage+"", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("Pk_userId",Pk_userId);
                parms.put("OldPassword",oldpassword);
                parms.put("NewPassword",newpassword);

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
