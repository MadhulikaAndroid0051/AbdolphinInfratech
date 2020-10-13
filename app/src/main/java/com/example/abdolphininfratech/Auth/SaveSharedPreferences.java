package com.example.abdolphininfratech.Auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.abdolphininfratech.Auth.PerferencesUtility.LOGGED_IN_PREF;

public class SaveSharedPreferences {

    static SharedPreferences getPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context,boolean loggedIn)
    {
        SharedPreferences.Editor editor=getPreferences(context).edit();

        editor.putBoolean(LOGGED_IN_PREF,loggedIn);
        editor.apply();
        editor.commit();
        editor.clear();
    }
    public static boolean getLoggedStatus(Context context)
    {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF,false);
    }
}
