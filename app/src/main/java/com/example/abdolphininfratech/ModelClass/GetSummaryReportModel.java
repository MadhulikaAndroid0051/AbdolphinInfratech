package com.example.abdolphininfratech.ModelClass;

public class GetSummaryReportModel {

    private String BranchName;
    private String BookingNumber;
    private String PlotNumber;
    private String Affiliate_Info;
    private String  CustomerName;
    private String ActualPlotAmount;
    private String NetPlotAmount;
    private String BalanceAmount;
    private String PaymentDate;


    public GetSummaryReportModel(String branchName, String bookingNumber, String plotNumber, String affiliate_Info, String customerName,
                                 String actualPlotAmount, String netPlotAmount, String balanceAmount, String paymentDate) {
        BranchName = branchName;
        BookingNumber = bookingNumber;
        PlotNumber = plotNumber;
        Affiliate_Info = affiliate_Info;
        CustomerName = customerName;
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

    public String getPlotNumber() {
        return PlotNumber;
    }

    public String getAffiliate_Info() {
        return Affiliate_Info;
    }

    public String getCustomerName() {
        return CustomerName;
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
}
