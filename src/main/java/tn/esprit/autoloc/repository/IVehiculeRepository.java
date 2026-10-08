package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.util.List;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {

    List<Vehicule> findByStatut(StatutVehicule statut);

    boolean existsByImmatriculation(String immatriculation);
}
