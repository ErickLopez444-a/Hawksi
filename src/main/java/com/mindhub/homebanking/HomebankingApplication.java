package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);


	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository,CardRepository cardRepository){
		return args -> {
			Client client1= new Client("Melba","Lorenzo","melba.lorenzo@mindhub.com",passwordEncoder.encode("hola1"));
			Client client2 = new Client("erick","lopez","ericklopez@gmail.com",passwordEncoder.encode("hola2"));

			Account account1 = new Account("VIN001", LocalDateTime.now(),5000);
			Account account2 = new Account("VIN002", LocalDateTime.now().plusDays(1), 8500);
			Account account3 = new Account("VIN003",LocalDateTime.now(),7000);

			Transaction transaction1 =new Transaction(TransactionType.DEBIT, -1000.0, "comida", LocalDateTime.now(),account1);
			Transaction transaction2 = new Transaction(TransactionType.CREDIT,1000,"escuela niños",LocalDateTime.now(),account1);
			Transaction transaction3 =new Transaction(TransactionType.DEBIT, -1000.0, "Supermercado", LocalDateTime.now(),account1);
			Transaction transaction4 = new Transaction(TransactionType.CREDIT,2000,"Pago Trabajo",LocalDateTime.now(),account1);
			Transaction transaction5 =new Transaction(TransactionType.DEBIT, -400.0, "comida", LocalDateTime.now(),account2);
			Transaction transaction6 = new Transaction(TransactionType.CREDIT,5000,"escuela niños",LocalDateTime.now(),account2);
			Transaction transaction7 =new Transaction(TransactionType.DEBIT, -3000.0, "Supermercado", LocalDateTime.now(),account2);
			Transaction transaction8 = new Transaction(TransactionType.CREDIT,2300,"Payday",LocalDateTime.now(),account2);
			Transaction transaction9 = new Transaction(TransactionType.CREDIT,2000,"PayDay",LocalDateTime.now(),account2);
			Transaction transaction10 =new Transaction(TransactionType.DEBIT, -400.0, "Grocery Shooping", LocalDateTime.now(),account2);
			Transaction transaction11= new Transaction(TransactionType.CREDIT,5000,"Kids School",LocalDateTime.now(),account2);
			Transaction transaction12 =new Transaction(TransactionType.DEBIT, -3000.0, "Supermarket", LocalDateTime.now(),account2);


			Loan loan1 = new Loan("Mortgage", 500000.00, List.of(6, 12, 24, 36, 48, 60),1.20);
			Loan loan2 = new Loan("Personal", 100000.00, List.of(6, 12, 24),1.12);
			Loan loan3 = new Loan("Vehicle", 300000.00, List.of(6, 12, 24, 36),1.15);




			clientRepository.save(client1);
			clientRepository.save(client2);


			client1.addAccount(account1);
			client1.addAccount(account2);
			client2.addAccount(account3);

			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);



			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
			transactionRepository.save(transaction7);
			transactionRepository.save(transaction8);
			transactionRepository.save(transaction9);
			transactionRepository.save(transaction10);
			transactionRepository.save(transaction11);
			transactionRepository.save(transaction12);

			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientloan1 = new ClientLoan(40000,60,client1,loan1);
			clientLoanRepository.save(clientloan1);

			ClientLoan clientloan2 = new ClientLoan(5000,12,client1,loan2);
			clientLoanRepository.save(clientloan2);

			Card card1 = new Card(client1.getFirstname() + "  " + client1.getLastname(), CardType.DEBIT, CardColor.GOLDEN, "4719-6630-7468-1920", 444, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client1 );
			cardRepository.save(card1);

			Card card2 = new Card(client1.getFirstname() + "  " + client1.getLastname(), CardType.CREDIT, CardColor.TITANIUM, "4915-6620-5764-234", 770, LocalDateTime.now(), LocalDateTime.now().plusYears(5),client1);
			cardRepository.save(card2);
		};
	}

}
