package com.example.abdolphininfratech.Auth;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String Loginid="LoginId",Password="Password";
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "codeplayon.com";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    public String getLoginid()
    {
       return pref.getString(Loginid,"");
    }
    public String getPassword()
    {
        return pref.getString(Password,"");
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(this.Password,Password);
        editor.commit();
    }

    public void setLoginid(String loginid) {
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(this.Loginid,Loginid);
        editor.commit();

    }
}
