package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repositories.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static com.mindhub.homebanking.models.CardType.CREDIT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;
    @Test
    public void existCards(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,is(not(empty())));
    }
    @Test
    public void existCardCredit(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,hasItem(hasProperty("type",is(CREDIT))));
    }
}
