package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {

    List<Vehicule> retrieveAllVehicules();

    Optional<Vehicule> retrieveVehicule(Long idVehicule);

    Vehicule addVehicule(Vehicule vehicule);

    void removeVehicule(Long idVehicule);

    Vehicule modifyVehicule(Vehicule vehicule);
}
