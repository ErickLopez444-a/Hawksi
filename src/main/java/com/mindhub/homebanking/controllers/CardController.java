package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/cards")
    public List<CardDTO>getcards(){
        return cardService.getcards();
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    @PostMapping("clients/current/cards")
   public ResponseEntity<Object>createcard(
           @RequestParam CardType type,
           @RequestParam CardColor color,
           Authentication authentication){

        Client client= clientService.getClientByEmail(authentication.getName());
        Set <Card> cards = client.getCards();
        Set <Card> cardDebit=cards.stream().filter(card ->card.getType()== CardType.DEBIT ).collect(Collectors.toSet());
        Set <Card> cardCredit=cards.stream().filter(card -> card.getType()== CardType.CREDIT).collect(Collectors.toSet());

        if (cardDebit.size()>= 3 && type == CardType.DEBIT){
            return new ResponseEntity<>("You can not have more than 3 debit cards",HttpStatus.FORBIDDEN);
        }

        if (cardCredit.size()>= 3 && type == CardType.CREDIT){
            return new ResponseEntity<>("You can not have more than 3 Credit cards",HttpStatus.FORBIDDEN);
        }

        int cvv = CardUtils.getRandomNumber(100,999);

        Card card = new Card(client.getFirstname()+""+client.getLastname(),type,color,CardUtils.getRandomNumber(1000,9999)+"-"+CardUtils.getRandomNumber(1000,9999)+"-"+CardUtils.getRandomNumber(1000,9999)+"-"+CardUtils.getRandomNumber(1000,9999),cvv,LocalDateTime.now(), LocalDateTime.now().plusYears(5),client);
        cardService.saveCard(card);
        return new ResponseEntity<>("Your card was created succesfully",HttpStatus.CREATED);
    }



}
