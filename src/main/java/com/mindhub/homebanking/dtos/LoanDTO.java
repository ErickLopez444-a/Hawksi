package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Loan;

import java.util.List;

public class LoanDTO {

    private long id;
    private String name;
    private List<Integer> payment;
    private double maxAmount;
    private double interest;

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.payment = loan.getPayments();
        this.maxAmount = loan.getMaxAmount();
        this.interest=loan.getInterests();
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPayment() {
        return payment;
    }

    public void setPayment(List<Integer> payment) {
        this.payment = payment;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getInterest() {
        return interest;
    }
}
