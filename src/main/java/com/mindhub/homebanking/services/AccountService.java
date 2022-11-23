package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {
    public List<AccountDTO> getAccountsDTO();
    public AccountDTO getAccountDTO( long id);

    void saveAccount(Account account);

    Account findByNumber(String number);
}
