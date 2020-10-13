package com.example.abdolphininfratech.ModelClass;

public class SummaryReportModal {

    private String BranchName;
    private String BookingNumber;
    private String  AffiliateInfo;
    private String CustomerInfo;
    private String Plot;
    private String ActualPlotAmount;
    private String NetPlotAmount;
    private String BalanceAmount;
    private String PaymentDate;

    public SummaryReportModal(String branchName, String bookingNumber, String affiliateInfo, String customerInfo,
                              String plot, String actualPlotAmount, String netPlotAmount, String balanceAmount, String paymentDate) {
        BranchName = branchName;
        BookingNumber = bookingNumber;
        AffiliateInfo = affiliateInfo;
        CustomerInfo = customerInfo;
        Plot = plot;
        ActualPlotAmount = actualPlotAmount;
        NetPlotAmount = netPlotAmount;
        BalanceAmount = balanceAmount;
        PaymentDate = paymentDate;
    }

    public String getBranchName() {
        return BranchName;
    }

    public String getBookingNumber() {
        return BookingNumber;
    }

    public String getAffiliateInfo() {
        return AffiliateInfo;
    }

    public String getCustomerInfo() {
        return CustomerInfo;
    }

    public String getPlot() {
        return Plot;
    }

    public String getActualPlotAmount() {
        return ActualPlotAmount;
    }

    public String getNetPlotAmount() {
        return NetPlotAmount;
    }

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public void setBookingNumber(String bookingNumber) {
        BookingNumber = bookingNumber;
    }

    public void setAffiliateInfo(String affiliateInfo) {
        AffiliateInfo = affiliateInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        CustomerInfo = customerInfo;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setActualPlotAmount(String actualPlotAmount) {
        ActualPlotAmount = actualPlotAmount;
    }

    public void setNetPlotAmount(String netPlotAmount) {
        NetPlotAmount = netPlotAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }
}
