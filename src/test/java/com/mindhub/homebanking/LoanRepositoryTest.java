package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRepositoryTest {
    @Autowired
    private LoanRepository loanRepository;
    @Test
    public void existLoans(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));
    }
    @Test
    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,hasItem(hasProperty("name",is("Personal"))));
    }
}
