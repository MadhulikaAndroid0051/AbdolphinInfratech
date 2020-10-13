package com.example.abdolphininfratech.ModelClass;

public class PlotBookingModel {

    private String Branch_name;
    private String Booking_no;
    private String Booking_date;
    private String Plot_details;
    private String Payment_plan;
    private String Customer_details;
    private String Affiliate_details;
    private String Plot_amt;
    private String PLC;
    private String Net_amnt;
    private String Paid_amnt;

    public PlotBookingModel(String branch_name, String booking_no, String booking_date, String plot_details, String payment_plan,
                            String customer_details, String affiliate_details, String plot_amt, String PLC, String net_amnt, String paid_amnt) {
        Branch_name = branch_name;
        Booking_no = booking_no;
        Booking_date = booking_date;
        Plot_details = plot_details;
        Payment_plan = payment_plan;
        Customer_details = customer_details;
        Affiliate_details = affiliate_details;
        Plot_amt = plot_amt;
        this.PLC = PLC;
        Net_amnt = net_amnt;
        Paid_amnt = paid_amnt;
    }

    public String getBranch_name() {
        return Branch_name;
    }

    public String getBooking_no() {
        return Booking_no;
    }

    public String getBooking_date() {
        return Booking_date;
    }

    public String getPlot_details() {
        return Plot_details;
    }

    public String getPayment_plan() {
        return Payment_plan;
    }

    public String getCustomer_details() {
        return Customer_details;
    }

    public String getAffiliate_details() {
        return Affiliate_details;
    }

    public String getPlot_amt() {
        return Plot_amt;
    }

    public String getPLC() {
        return PLC;
    }

    public String getNet_amnt() {
        return Net_amnt;
    }

    public String getPaid_amnt() {
        return Paid_amnt;
    }

    public void setBranch_name(String branch_name) {
        Branch_name = branch_name;
    }

    public void setBooking_no(String booking_no) {
        Booking_no = booking_no;
    }

    public void setBooking_date(String booking_date) {
        Booking_date = booking_date;
    }

    public void setPlot_details(String plot_details) {
        Plot_details = plot_details;
    }

    public void setPayment_plan(String payment_plan) {
        Payment_plan = payment_plan;
    }

    public void setCustomer_details(String customer_details) {
        Customer_details = customer_details;
    }

    public void setAffiliate_details(String affiliate_details) {
        Affiliate_details = affiliate_details;
    }

    public void setPlot_amt(String plot_amt) {
        Plot_amt = plot_amt;
    }

    public void setPLC(String PLC) {
        this.PLC = PLC;
    }

    public void setNet_amnt(String net_amnt) {
        Net_amnt = net_amnt;
    }

    public void setPaid_amnt(String paid_amnt) {
        Paid_amnt = paid_amnt;
    }
}
