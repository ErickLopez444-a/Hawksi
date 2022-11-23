package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {
    public List<LoanDTO> getloansDTO();

    Loan findById(long id);

    void saveLoan(Loan loan);
}
