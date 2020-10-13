package com.example.abdolphininfratech.Fragement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.abdolphininfratech.Auth.AppConstent;
import com.example.abdolphininfratech.Auth.PerferencesUtility;
import com.example.abdolphininfratech.Auth.PrefManager;
import com.example.abdolphininfratech.Auth.SaveSharedPreferences;
import com.example.abdolphininfratech.Auth.SessionHandler;
import com.example.abdolphininfratech.MainActivity;
import com.example.abdolphininfratech.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {

    TextView reset_password;
    Button btn_login;
    TextView btn_signup;
    EditText et_username,et_password;
    CheckBox show_password;
    ImageView show_password_icon;
    SharedPreferences sharedpreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        btn_login=view.findViewById(R.id.btn_login);
        reset_password=view.findViewById(R.id.reset_password);
       // btn_signup=view.findViewById(R.id.btn_signup);
        et_username=view.findViewById(R.id.et_username);
        et_password=view.findViewById(R.id.et_password);
    /*    show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b)
                {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });*/


        if(SaveSharedPreferences.getLoggedStatus(getContext()))
        {
            Intent intent=new Intent(getContext(), MainActivity.class);

            startActivity(intent);
            getActivity().finish();


        }
        else
        {
            btn_login.setVisibility(View.VISIBLE);
        }

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = null;
                mFragment = new ForgotPasswordFRagment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).addToBackStack("login").commit();

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_username.length() > 0 && et_password.length() > 0) {
                    final String user=et_username.getText().toString().trim();
                    final String paas=et_password.getText().toString().trim();
                    getLogin(user,paas);

                }
                else
                {
                    Toast.makeText(getContext(), "Fill value", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    public void getLogin(final String user, final String paas)
    {

        String loginUrl="http://test.abdolphininfratech.com/WebAPI/Login";
       String loginUrl1=" http://test.abdolphininfratech.com/WebAPI/Login?LoginId="+user+"&Password="+paas+"";
        String url = "http://awasapi.thinkcomputers.in/api/Awas/GetLogin?UserName="+user+"&Password="+paas+"";

        StringRequest stringRequest=new StringRequest(Request.Method.POST,loginUrl1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    final String  status=jsonObject.getString("Status");
                    final String message=jsonObject.getString("SuccessMessage");
                    final String errormessage=jsonObject.getString("ErrorMessage");
                   // Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT).show();


                    if (status.equalsIgnoreCase("0")&& message.equalsIgnoreCase("Successfully logged in!"))
                    {
                        SaveSharedPreferences.setLoggedIn(getContext(),true);
                        Toast.makeText(getContext(), message+"", Toast.LENGTH_SHORT).show();
                        Intent I=new Intent(getContext(), MainActivity.class);
                        String loginId=jsonObject.getString("LoginId");
                        String password=jsonObject.getString("Password");
                        String Pk_userId=jsonObject.getString("Pk_userId");

                        SessionHandler.getInstance().save(getContext(),PerferencesUtility.LoginID ,loginId);
                        SessionHandler.getInstance().save(getContext(), PerferencesUtility.Pk_userId,Pk_userId);

                        I.setFlags(I.FLAG_ACTIVITY_CLEAR_TASK | I.FLAG_ACTIVITY_CLEAR_TOP | I.FLAG_ACTIVITY_CLEAR_TASK);
                         startActivity(I);

                       //   SessionHandler.getInstance().save(MainActivity.this,AppConstent.username,user);
                        // SessionHandler.getInstance().save(getContext(), AppConstent.userID, loginid);

                    }
                    else
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
        })/*{
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("LoginId",user);
                parms.put("Password", paas);
                return parms;

            }
        }*/;
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}
