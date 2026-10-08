package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IContratService {

    List<Contrat> retrieveAllContrats();

    Optional<Contrat> retrieveContrat(Long idContrat);

    Contrat addContrat(Contrat contrat);

    void removeContrat(Long idContrat);

    Contrat modifyContrat(Contrat contrat);

    Contrat affecterReservation(Long idContrat, Long idReservation);

    Paiement ajouterPaiement(Long idContrat, Paiement paiement);
}
