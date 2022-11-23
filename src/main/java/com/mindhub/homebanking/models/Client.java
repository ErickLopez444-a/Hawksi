package com.mindhub.homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstname;
    private String lastname;
    private String email;

    private String password;

    @OneToMany(mappedBy="client", fetch= FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
     Set<ClientLoan> clientLoans = new HashSet<>();


    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
     Set<Card> cards = new HashSet<>();


    public Client() {

    }

    public Client(String firstname, String lastname, String email,String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password=password;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Account> getAccounts(){return accounts;}

    public Set<ClientLoan> getClientLoan() {
        return clientLoans;
    }

    public Set <Card> getCards(){ return cards;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //
    public void addAccount(Account account){
        account.setClient(this);
        accounts.add(account);
    }


}

