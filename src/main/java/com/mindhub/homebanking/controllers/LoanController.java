package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CreateLoanDTO;
import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanCreationDto;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private ClientLoanService clientLoanService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public List<LoanDTO> getloansDTO(){
      return loanService.getloansDTO();
    }

    @PostMapping("/post/loan")
    public ResponseEntity<?> createLoan(Authentication authentication, @RequestBody CreateLoanDTO createLoanDTO){
        if (createLoanDTO.getInterest() == 0 || createLoanDTO.getMaxAmount() == 0 || createLoanDTO.getName().isEmpty() || createLoanDTO.getPayments().isEmpty()){
            return new ResponseEntity<>("Admin data is missing please check again", HttpStatus.FORBIDDEN);
        }
        loanService.saveLoan(new Loan(createLoanDTO.getName(), createLoanDTO.getMaxAmount(), createLoanDTO.getPayments(),createLoanDTO.getInterest()));
        return new ResponseEntity<>("your loans been created succsesfuly", HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object>requestLoan(  Authentication authentication,@RequestBody LoanApplicationDTO loanApplicationDTO){
        Client clientcurrent=clientService.getClientByEmail(authentication.getName());
        Account accountdestiny=accountService.findByNumber(loanApplicationDTO.getAccountNumber());
        Loan loan = loanService.findById(loanApplicationDTO.getId());
        Set<ClientLoan> loanSet=clientcurrent.getClientLoan();
        Set<ClientLoan> loans= loanSet.stream().filter(clientLoan -> clientLoan.getLoan()== loan).collect(Collectors.toSet());

        if (loans.size()>= 1 && loan.getName().equals("Mortgage")){
            return new ResponseEntity<>("You Need to Finish Paying your Mortgage loan first,Before asking for another",HttpStatus.FORBIDDEN);
        }
        if (loans.size()>= 1 && loan.getName().equals("Personal")){
            return new ResponseEntity<>("You ALREADY HAVE A Personal LOAN",HttpStatus.FORBIDDEN);
        }
        if (loans.size()>= 1 && loan.getName().equals("Vehicle")){
            return new ResponseEntity<>("You ALREADY HAVE A Vehicle LOAN",HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getId()<=0){
            return new ResponseEntity<>("Please select a credit type", HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getAmount()<=0){
            return new ResponseEntity<>("Please select that amount that fits you the most",HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getPayments()<=0){
            return new ResponseEntity<>("Please Make sure to select the amount of payments",HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAccountNumber().isEmpty()){
            return new ResponseEntity<>("Please select the account destiny",HttpStatus.FORBIDDEN);
        }



        if (loan == null){
            return new ResponseEntity<>("The loan does not exist",HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmount()>loan.getMaxAmount()){
            return new ResponseEntity<>("The amount that you are requesting is more than the one we can offer,please check again",HttpStatus.FORBIDDEN);
        }
        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("The amount of payments requested is not among those established for this credit",HttpStatus.FORBIDDEN);
        }


        if (accountdestiny==null){
            return new ResponseEntity<>("The account you entered doesnt exist",HttpStatus.FORBIDDEN);
        }


        if (!clientcurrent.getAccounts().contains(accountdestiny)){
            return new ResponseEntity<>("The transaction is not allowed",HttpStatus.FORBIDDEN);
        }


        Transaction transaction1=new Transaction(TransactionType.CREDIT,loanApplicationDTO.getAmount(),loan.getName()+"Your loan been aproved", LocalDateTime.now(),accountdestiny);
       transactionService.saveTransactions(transaction1);

        accountdestiny.setBalance(accountdestiny.getBalance()+loanApplicationDTO.getAmount());
        accountService.saveAccount(accountdestiny);

        if (loan.getName().equals("Mortgage")){
            loanApplicationDTO.setAmount(loanApplicationDTO.getAmount()*1.20);//intereses
        }
        if (loan.getName().equals("Personal")){
            loanApplicationDTO.setAmount(loanApplicationDTO.getAmount()*1.12);
        }
        if (loan.getName().equals("Vehicle")){
            loanApplicationDTO.setAmount(loanApplicationDTO.getAmount()*1.15);
        }

        ClientLoan clientLoan1= new ClientLoan(loanApplicationDTO.getAmount(),loanApplicationDTO.getPayments(),clientcurrent,loan);
        clientLoanService.saveClientLoan(clientLoan1);
        return new ResponseEntity<>("Credit Approved",HttpStatus.CREATED);

    }
}
