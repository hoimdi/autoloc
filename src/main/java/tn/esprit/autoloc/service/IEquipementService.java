package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;
import java.util.Optional;

public interface IEquipementService {

    List<Equipement> retrieveAllEquipements();

    Optional<Equipement> retrieveEquipement(Long idEquipement);

    Equipement addEquipement(Equipement equipement);

    void removeEquipement(Long idEquipement);

    Equipement modifyEquipement(Equipement equipement);
}
