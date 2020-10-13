package com.example.abdolphininfratech.ModelClass;

public class Lstassociate {

    private String customerID;
    private String customerName;
    private String plotNumber;
    private String installmentNo;
    private String installmentAmount;

    public Lstassociate(String customerID, String customerName, String plotNumber, String installmentNo, String installmentAmount) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.plotNumber = plotNumber;
        this.installmentNo = installmentNo;
        this.installmentAmount = installmentAmount;

    }

    public String getCustomerID() {
        return customerID;
    }


    public String getCustomerName() {
        return customerName;
    }

    public String getInstallmentNo() {
        return installmentNo;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setInstallmentNo(String installmentNo) {
        this.installmentNo = installmentNo;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }
}
