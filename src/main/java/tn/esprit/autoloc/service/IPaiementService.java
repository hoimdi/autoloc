package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;
import java.util.Optional;

public interface IPaiementService {

    List<Paiement> retrieveAllPaiements();

    Optional<Paiement> retrievePaiement(Long idPaiement);

    Paiement addPaiement(Paiement paiement);

    void removePaiement(Long idPaiement);

    Paiement modifyPaiement(Paiement paiement);
}
