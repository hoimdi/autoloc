package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {

    List<Vehicule> retrieveAllVehicules();

    List<Vehicule> retrieveVehiculesByStatut(StatutVehicule statut);

    Vehicule getVehiculeById(Long id);

    Optional<Vehicule> retrieveVehicule(Long idVehicule);

    Vehicule addVehicule(Vehicule vehicule, Long idAgence, List<Long> idsEquipements);

    void removeVehicule(Long idVehicule);

    Vehicule modifyVehicule(Vehicule vehicule, Long idAgence, List<Long> idsEquipements);
}
