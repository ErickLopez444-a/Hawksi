package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Test
    public void accountsExist(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, is(not(empty())));
    }
    @Test
    public void numberAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts,hasItem(hasProperty("number",is("VIN001"))));
    }
}
