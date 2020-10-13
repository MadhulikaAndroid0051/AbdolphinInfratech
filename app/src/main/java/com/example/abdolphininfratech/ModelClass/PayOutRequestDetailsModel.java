package com.example.abdolphininfratech.ModelClass;

public class PayOutRequestDetailsModel {

    private String req_LoginID;
    private String req_FirstName;
    private String req_RequestedDate;
    private String req_Amount;
    private String req_status;


    public PayOutRequestDetailsModel(String req_LoginID, String req_FirstName, String req_RequestedDate, String req_Amount, String req_status) {
        this.req_LoginID = req_LoginID;
        this.req_FirstName = req_FirstName;
        this.req_RequestedDate = req_RequestedDate;
        this.req_Amount = req_Amount;
        this.req_status = req_status;
    }

    public String getReq_LoginID() {
        return req_LoginID;
    }

    public String getReq_FirstName() {
        return req_FirstName;
    }

    public String getReq_RequestedDate() {
        return req_RequestedDate;
    }

    public String getReq_Amount() {
        return req_Amount;
    }

    public String getReq_status() {
        return req_status;
    }

    public void setReq_LoginID(String req_LoginID) {
        this.req_LoginID = req_LoginID;
    }

    public void setReq_FirstName(String req_FirstName) {
        this.req_FirstName = req_FirstName;
    }

    public void setReq_RequestedDate(String req_RequestedDate) {
        this.req_RequestedDate = req_RequestedDate;
    }

    public void setReq_Amount(String req_Amount) {
        this.req_Amount = req_Amount;
    }

    public void setReq_status(String req_status) {
        this.req_status = req_status;
    }
}
