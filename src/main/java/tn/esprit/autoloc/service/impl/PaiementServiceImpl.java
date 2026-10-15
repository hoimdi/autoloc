package tn.esprit.autoloc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IPaiementRepository;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final IContratRepository contratRepository;

    @Override
    public List<Paiement> retrieveAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Optional<Paiement> retrievePaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement);
    }

    @Override
    public Paiement addPaiement(Paiement paiement) {
        affecterContrat(paiement);
        return paiementRepository.save(paiement);
    }

    @Override
    public void removePaiement(Long idPaiement) {
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    public Paiement modifyPaiement(Paiement paiement) {
        affecterContrat(paiement);
        return paiementRepository.save(paiement);
    }

    private void affecterContrat(Paiement paiement) {
        if (paiement.getContrat() != null && paiement.getContrat().getIdContrat() != null) {
            Contrat contratRef = contratRepository.getReferenceById(paiement.getContrat().getIdContrat());
            paiement.setContrat(contratRef);
        }
    }
}
