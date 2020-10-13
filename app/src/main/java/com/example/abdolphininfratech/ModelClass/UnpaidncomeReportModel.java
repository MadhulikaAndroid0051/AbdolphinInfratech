package com.example.abdolphininfratech.ModelClass;

public class UnpaidncomeReportModel {

    private String Cosing_Date;
    private String From_ID;
    private String From_Name;
    private String To_ID;
    private String To_Name;
    private String Business_Amount;
    private String DifferencePercentage;
    private String Income;

    public UnpaidncomeReportModel(String cosing_Date, String from_ID, String from_Name, String to_ID, String to_Name, String business_Amount, String differencePercentage, String income) {
        Cosing_Date = cosing_Date;
        From_ID = from_ID;
        From_Name = from_Name;
        To_ID = to_ID;
        To_Name = to_Name;
        Business_Amount = business_Amount;
        DifferencePercentage = differencePercentage;
        Income = income;
    }

    public String getCosing_Date() {
        return Cosing_Date;
    }

    public String getFrom_ID() {
        return From_ID;
    }

    public String getFrom_Name() {
        return From_Name;
    }

    public String getTo_ID() {
        return To_ID;
    }

    public String getTo_Name() {
        return To_Name;
    }

    public String getBusiness_Amount() {
        return Business_Amount;
    }

    public String getDifferencePercentage() {
        return DifferencePercentage;
    }

    public String getIncome() {
        return Income;
    }

    public void setCosing_Date(String cosing_Date) {
        Cosing_Date = cosing_Date;
    }

    public void setFrom_ID(String from_ID) {
        From_ID = from_ID;
    }

    public void setFrom_Name(String from_Name) {
        From_Name = from_Name;
    }

    public void setTo_ID(String to_ID) {
        To_ID = to_ID;
    }

    public void setTo_Name(String to_Name) {
        To_Name = to_Name;
    }

    public void setBusiness_Amount(String business_Amount) {
        Business_Amount = business_Amount;
    }

    public void setDifferencePercentage(String differencePercentage) {
        DifferencePercentage = differencePercentage;
    }

    public void setIncome(String income) {
        Income = income;
    }
}
