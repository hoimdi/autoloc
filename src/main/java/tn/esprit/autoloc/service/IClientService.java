package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<Client> retrieveAllClients();

    Optional<Client> retrieveClient(Long idClient);

    Optional<Client> retrieveClientByEmail(String email);

    Client addClient(Client client);

    void removeClient(Long idClient);

    Client modifyClient(Client client);
}
