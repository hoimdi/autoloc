package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.domain.ModePaiement;

import java.util.List;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContratIdContrat(Long idContrat);

    List<Paiement> findByModePaiement(ModePaiement modePaiement);
}
