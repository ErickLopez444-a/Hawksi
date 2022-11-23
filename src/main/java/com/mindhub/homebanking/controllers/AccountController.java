package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccountsDTO();
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccounts(@PathVariable long id){
        return accountService.getAccountDTO(id);
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> newAccount(Authentication authentication){
        Client client = clientService.getClientByEmail(authentication.getName());
        if (client.getAccounts().size() >= 3){
            return new ResponseEntity<>("No puedes tener mas de tres cuentas", HttpStatus.FORBIDDEN);
        }
     Account account= new Account("VIN"+getRandomNumber(1000000,10000000), LocalDateTime.now(),0);
        client.addAccount(account);
      accountService.saveAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }
}
