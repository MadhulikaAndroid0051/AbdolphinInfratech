package com.example.abdolphininfratech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.abdolphininfratech.Auth.SaveSharedPreferences;
import com.example.abdolphininfratech.Fragement.AssociateDownlineDetailFragment;
import com.example.abdolphininfratech.Fragement.ChangePasswordFragment;
import com.example.abdolphininfratech.Fragement.PayOutDetailsSearchFragment;
import com.example.abdolphininfratech.Fragement.PayOutRequestFragment;
import com.example.abdolphininfratech.Fragement.PayOutRequestReportFragment;
import com.example.abdolphininfratech.Fragement.PayoutDetailsFragment;
import com.example.abdolphininfratech.Fragement.PayoutLedgerFragment;
import com.example.abdolphininfratech.Fragement.PayoutRequestReportSearchFragment;
import com.example.abdolphininfratech.Fragement.PloatBookingDetailsFragment;
import com.example.abdolphininfratech.Fragement.PlotAvailabilityDetailsFragment;
import com.example.abdolphininfratech.Fragement.PlotAvailabilityFragment;
import com.example.abdolphininfratech.Fragement.PlotBookingFragment;
import com.example.abdolphininfratech.Fragement.SummaryReportFragment;
import com.example.abdolphininfratech.Fragement.SummaryReportSearchFragment;
import com.example.abdolphininfratech.Fragement.UnpaidReportSearchFragment;
import com.example.abdolphininfratech.Fragement.UnpaidncomeReportFragment;
import com.example.abdolphininfratech.Fragement.ViewProfileFragment;
import com.example.abdolphininfratech.navigation.BaseItem;
import com.example.abdolphininfratech.navigation.CustomDataProvider;
import com.example.abdolphininfratech.view.LevelBeamView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import pl.openrnd.multilevellistview.ItemInfo;
import pl.openrnd.multilevellistview.MultiLevelListAdapter;
import pl.openrnd.multilevellistview.MultiLevelListView;
import pl.openrnd.multilevellistview.OnItemClickListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private MultiLevelListView multiLevelListView;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        private void showItemDescription(Object object, ItemInfo itemInfo) {

            if (((BaseItem) object).getName().contains("Home")) {
                displaySelectedScreen("HOME");
            }
            if (((BaseItem) object).getName().contains("Booking Details")) {
                displaySelectedScreen("REPORTS1");
            }
            if (((BaseItem) object).getName().contains("Plot Availability")) {
                displaySelectedScreen("REPORTS2");
            }
            if (((BaseItem) object).getName().contains("Customer Ledger Report")) {
                displaySelectedScreen("REPORTS3");
            }
            if (((BaseItem) object).getName().contains("Affiliate Downline")) {
                displaySelectedScreen("REPORTS4");
            }
            if (((BaseItem) object).getName().contains("Unpaid Income Report")) {
                displaySelectedScreen("REPORTS5");
            }
            if (((BaseItem) object).getName().contains("Payout Request Report")) {
                displaySelectedScreen("REPORTS6");
            }
            if (((BaseItem) object).getName().contains("Summary Report")) {
                displaySelectedScreen("REPORTS7");
            }


if (((BaseItem)object).getName().contains("PayOuts Details"))
{
    displaySelectedScreen("PayOuts Details");
}
if (((BaseItem) object).getName().contains("PayOuts Request"))
            {
               displaySelectedScreen(("PayOuts Request"));
            }
if (((BaseItem)object).getName().contains("PayOuts Ledger"))
            {
                displaySelectedScreen("PayOuts Ledger");
            }
if (((BaseItem)object).getName().contains("Change Password"))
{
    displaySelectedScreen("Change Password");
}

            if (((BaseItem) object).getName().contains("View Profile")) {
                displaySelectedScreen("View Profile");
            }
            if (((BaseItem) object).getName().contains("About Us")) {
                displaySelectedScreen("ABOUTUS");
            }


        }

        @Override
        public void onItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }

        @Override
        public void onGroupItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        confMenu();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen("HOME");


    }

    private void confMenu() {
        multiLevelListView = findViewById(R.id.multi_nav);
        // custom ListAdapter
        ListAdapter listAdapter = new ListAdapter();
        multiLevelListView.setAdapter(listAdapter);
        multiLevelListView.setOnItemClickListener(mOnItemClickListener);
        listAdapter.setDataItems(CustomDataProvider.getInitialItems());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            SaveSharedPreferences.setLoggedIn(getApplicationContext(),false);
            Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            Intent I=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(I);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(String itemName) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemName) {
            case "HOME":
                fragment = new HomeFragment();
                break;
            case "REPORTS1":
                fragment = new PloatBookingDetailsFragment();
                break;
            case "REPORTS2":
                fragment = new PlotAvailabilityFragment();
                break;
            case "REPORTS3":
                fragment = new PayoutLedgerFragment();
                break;
            case "REPORTS4":
                fragment = new AssociateDownlineDetailFragment();
                break;
            case "REPORTS5":
                fragment = new UnpaidReportSearchFragment();
                break;
            case "REPORTS6":
                fragment = new PayoutRequestReportSearchFragment();
                break;
            case "REPORTS7":
                fragment = new SummaryReportSearchFragment();
                break;

              case "PayOuts Request":
                fragment=new PayOutRequestFragment();
                break;
            case "PayOuts Details":
                fragment=new PayOutDetailsSearchFragment();
                break;
            case "PayOuts Ledger":
                fragment=new PayoutLedgerFragment();
                break;
            case "View Profile":
                fragment=new ViewProfileFragment();
                break;
            case "Change Password":
                fragment=new ChangePasswordFragment();
                break;
            case "ABOUTUS":
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                break;


        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method display selected screen and passing the id of selected menu
        displaySelectedScreen(String.valueOf(item.getItemId()));
        //make this method blank
        return true;
    }


    private class ListAdapter extends MultiLevelListAdapter {

        @Override
        public List<?> getSubObjects(Object object) {
            return CustomDataProvider.getSubItems((BaseItem) object);
        }

        @Override
        public boolean isExpandable(Object object) {
            return CustomDataProvider.isExpandable((BaseItem) object);
        }

        @SuppressLint("InflateParams")
        @Override
        public View getViewForObject(Object object, View convertView, ItemInfo itemInfo) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.data_item, null);
                viewHolder.nameView = convertView.findViewById(R.id.dataItemName);
                viewHolder.arrowView = convertView.findViewById(R.id.dataItemArrow);
                viewHolder.icon = convertView.findViewById(R.id.di_image);
                viewHolder.levelBeamView = convertView.findViewById(R.id.dataItemLevelBeam);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.nameView.setText(((BaseItem) object).getName());
            if (itemInfo.isExpandable()) {
                viewHolder.arrowView.setVisibility(View.VISIBLE);
                viewHolder.arrowView.setImageResource(itemInfo.isExpanded() ?
                        R.drawable.ic_baseline_arrow_forward_ios_24 : R.drawable.ic_baseline_arrow_forward_ios_24);
            } else {
                viewHolder.arrowView.setVisibility(View.GONE);
            }
            viewHolder.icon.setImageResource(((BaseItem) object).getIcon());
            return convertView;
        }

        private class ViewHolder {
            TextView nameView;
            ImageView arrowView;
            ImageView icon;
            LevelBeamView levelBeamView;
        }
    }
}
