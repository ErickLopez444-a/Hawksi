package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private Set<AccountDTO> accounts;
      Set <ClientLoanDTO> clientLoans;
      Set<CardDTO> clientCard ;




    public ClientDTO() {
    }
    public ClientDTO(Client client) {
        this.id=client.getId();
        this.firstname= client.getFirstname();
        this.lastname= client.getLastname();
        this.email= client.getEmail();
        this.accounts=client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toSet());
        this.clientLoans=client.getClientLoan().stream().map(clientLoan -> new ClientLoanDTO(clientLoan)).collect(Collectors.toSet());
        this.clientCard=client.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());

    }

    public long getId() {return id;}

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Set<AccountDTO> getAccounts(){return accounts;}
    public Set<ClientLoanDTO> getLoans(){return clientLoans;}
    public  Set <CardDTO> getCards(){
        return  clientCard;
    }


}
