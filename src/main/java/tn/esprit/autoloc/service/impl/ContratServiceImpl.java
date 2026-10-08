package tn.esprit.autoloc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IPaiementRepository;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final IReservationRepository reservationRepository;
    private final IPaiementRepository paiementRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Optional<Contrat> retrieveContrat(Long idContrat) {
        return contratRepository.findById(idContrat);
    }

    @Override
    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public void removeContrat(Long idContrat) {
        contratRepository.deleteById(idContrat);
    }

    @Override
    public Contrat modifyContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat affecterReservation(Long idContrat, Long idReservation) {
        Contrat contrat = contratRepository.findById(idContrat)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable : " + idContrat));
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new IllegalArgumentException("Reservation introuvable : " + idReservation));
        contrat.setReservation(reservation);
        return contratRepository.save(contrat);
    }

    @Override
    public Paiement ajouterPaiement(Long idContrat, Paiement paiement) {
        Contrat contrat = contratRepository.findById(idContrat)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable : " + idContrat));
        paiement.setContrat(contrat);
        return paiementRepository.save(paiement);
    }
}
