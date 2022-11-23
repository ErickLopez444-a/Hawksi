package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;

import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


public class AccountDTO {
    private long id;

    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private Set<TransactionDTO> transactionDTO;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.id=account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transactionDTO = account.getTransactions().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getNumber() {return number;}

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }
    public Set<TransactionDTO> getTransaction() {
        return transactionDTO;
    }


    public String toString() {
        return "Account{" +
                "id=" + id +
                ", Number='" + number + '\'' +
                ", Creation-Date='" + creationDate + '\'' +
                ", Balance='" + balance + '\'' +
                '}';
    }

}
