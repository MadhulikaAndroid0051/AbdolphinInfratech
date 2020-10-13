package com.example.abdolphininfratech.navigation;


import android.content.Context;

import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;



public class CustomDataProvider {

    private static final int MAX_LEVELS = 3;

    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static List<BaseItem> mMenu = new ArrayList<>();
    Context context;

    public static List<BaseItem> getInitialItems() {
        //return getSubItems(new GroupItem("root"));

        List<BaseItem> rootMenu = new ArrayList<>();

        rootMenu.add(new Item("Home", R.drawable.ic_baseline_arrow_forward_ios_24));
        rootMenu.add(new GroupItem("Reports",R.drawable.ic_baseline_arrow_forward_ios_24));
       // rootMenu.add(new GroupItem("PayOuts", R.drawable.ic_baseline_arrow_forward_ios_24));
        //rootMenu.add(new GroupItem("",))
        rootMenu.add(new Item("PayOuts Request",R.drawable.ic_baseline_arrow_forward_ios_24));
        rootMenu.add(new Item("PayOuts Details",R.drawable.ic_baseline_arrow_forward_ios_24));
        rootMenu.add(new Item("PayOuts Ledger",R.drawable.ic_baseline_arrow_forward_ios_24));
        rootMenu.add(new Item("View Profile", R.drawable.ic_baseline_arrow_forward_ios_24));
        rootMenu.add(new Item("Change Password",R.drawable.ic_baseline_arrow_forward_ios_24));
        rootMenu.add(new Item("About Us", R.drawable.ic_baseline_arrow_forward_ios_24));

        return rootMenu;
    }

    public static List<BaseItem> getSubItems(BaseItem baseItem) {

        List<BaseItem> result = new ArrayList<>();
        int level = ((GroupItem) baseItem).getLevel() + 1;
        String menuItem = baseItem.getName();

        GroupItem groupItem = (GroupItem) baseItem;
        if (groupItem.getLevel() >= MAX_LEVELS) {
            return null;
        }

        if (level == LEVEL_1) {
            switch (menuItem.toUpperCase()) {
                case "REPORTS":
                    result = getListCategory();
                    break;
                /*case "PAYOUTS":
                    result = getListAssignments();
                    break;*/
            }
        }
        return result;
    }

    public static boolean isExpandable(BaseItem baseItem) {
        return baseItem instanceof GroupItem;
    }

    private static List<BaseItem> getListCategory() {
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Booking Details"));
        list.add(new Item("Plot Availability"));
        list.add(new Item("Customer Ledger Report"));
        list.add(new Item("Affiliate Downline"));
        list.add(new Item("Unpaid Income Report"));
        list.add(new Item("Payout Request Report"));
        list.add(new Item("Summary Report"));


        return list;
    }

    /*private static List<BaseItem> getListAssignments() {
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Payout Details"));
        list.add(new Item("Payout Ledger"));
        list.add(new Item("Payout Details"));
        return list;
    }*/
}
