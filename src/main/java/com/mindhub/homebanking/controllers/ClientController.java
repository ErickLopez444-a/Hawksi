package com.mindhub.homebanking.controllers;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired //IN
    ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientService.getClientsDTO();
    }
    @GetMapping("clients/{id}")
    public ClientDTO getClient(@PathVariable long id){
        return clientService.getClientDTO(id);
    }



    @GetMapping("clients/current")
    public ClientDTO getAuthClient(Authentication authentication){
        return new ClientDTO(clientService.getClientByEmail(authentication.getName()));
    }


    @PostMapping( "/clients")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {



        if (firstName.isEmpty()) {

            return new ResponseEntity<>("Missing firstname", HttpStatus.FORBIDDEN);
        }
        if (lastName.isEmpty()) {

            return new ResponseEntity<>("Missing Last Name", HttpStatus.FORBIDDEN);

        }
        if (email.isEmpty()) {

            return new ResponseEntity<>("Missing email", HttpStatus.FORBIDDEN);

        }
        if (password.isEmpty()) {

            return new ResponseEntity<>("Missing password", HttpStatus.FORBIDDEN);

        }


        if (clientService.getClientByEmail(email) !=  null)  {

            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);

        }


        Client newClient=new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.saveClient(newClient);

        Account newAccount = new Account("VIN"+getRandomNumber(10000000,10000000), LocalDateTime.now(),0);
        newClient.addAccount(newAccount);
        accountService.saveAccount(newAccount);


        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min );
    }

}
