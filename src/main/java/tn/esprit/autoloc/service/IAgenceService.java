package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;
import java.util.Optional;

public interface IAgenceService {

    List<Agence> retrieveAllAgences();

    Optional<Agence> retrieveAgence(Long idAgence);

    Agence addAgence(Agence agence);

    void removeAgence(Long idAgence);

    Agence modifyAgence(Agence agence);
}
