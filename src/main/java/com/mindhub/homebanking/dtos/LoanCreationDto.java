package com.mindhub.homebanking.dtos;

import java.util.List;

public class LoanCreationDto {
    private long id;
    private String name;
    private List<Integer> payment;
    private double maxAmount;
    private double interest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
