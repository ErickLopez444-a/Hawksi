package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {
    public List<ClientDTO> getClientsDTO();

    public Client getClientCurrent(Authentication authentication);

    ClientDTO getClientDTO(Long id);

    void saveClient(Client client);

    Client getClientByEmail(String email);
}
