package com.example.abdolphininfratech.Fragement;

public class PayoutLedgerModal {
    private String  TransactionDate;
    private String  Narration;
    private String CreditAmount;
    private String  Debit;

    public PayoutLedgerModal(String transactionDate, String narration, String creditAmount, String debit) {
        TransactionDate = transactionDate;
        Narration = narration;
        CreditAmount = creditAmount;
        Debit = debit;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public String getNarration() {
        return Narration;
    }

    public String getCreditAmount() {
        return CreditAmount;
    }

    public String getDebit() {
        return Debit;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }

    public void setNarration(String narration) {
        Narration = narration;
    }

    public void setCreditAmount(String creditAmount) {
        CreditAmount = creditAmount;
    }

    public void setDebit(String debit) {
        Debit = debit;
    }

    }
