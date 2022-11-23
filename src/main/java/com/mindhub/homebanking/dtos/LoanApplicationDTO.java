package com.mindhub.homebanking.dtos;

public class LoanApplicationDTO {

    private long id;
    private double amount;
    private  int payments;
    private String accountNumber;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(long id, double amount, int payments, String accountNumber) {
        this.id = id;
        this.amount = amount;
        this.payments = payments;
        this.accountNumber =accountNumber;
    }

    public long getId() {
        return id;
    }

    public void setLoanId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
