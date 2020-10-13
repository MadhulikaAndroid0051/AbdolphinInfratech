package com.example.abdolphininfratech.ModelClass;

public class PayoutDetailsModal {

    private String ClosingDate;
    private String PayOutNo;
    private String LoginID;
    private String FirstName;
    private String GrossAmount;
    private String Processing;
    private String NetAmount;

    public PayoutDetailsModal(String closingDate, String payOutNo, String loginID, String firstName, String grossAmount, String processing, String netAmount) {
        ClosingDate = closingDate;
        PayOutNo = payOutNo;
        LoginID = loginID;
        FirstName = firstName;
        GrossAmount = grossAmount;
        Processing = processing;
        NetAmount = netAmount;
    }

    public String getClosingDate() {
        return ClosingDate;
    }

    public String getPayOutNo() {
        return PayOutNo;
    }

    public String getLoginID() {
        return LoginID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getGrossAmount() {
        return GrossAmount;
    }

    public String getProcessing() {
        return Processing;
    }

    public String getNetAmount() {
        return NetAmount;
    }
}
