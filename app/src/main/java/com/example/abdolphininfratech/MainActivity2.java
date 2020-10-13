package com.example.abdolphininfratech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.abdolphininfratech.Fragement.LoginFragment;

public class MainActivity2 extends AppCompatActivity {


    LinearLayout linearLayout,linearLayout1;
    String pageValue;
    private LoginFragment login_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Fragment mFragment = null;
        mFragment = new LoginFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.linearlayout,mFragment).commit();

    }
}