package tn.esprit.autoloc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;

    @Override
    public List<Vehicule> retrieveAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public List<Vehicule> retrieveVehiculesByStatut(StatutVehicule statut) {
        return vehiculeRepository.findByStatut(statut);
    }

    @Override
    public Vehicule getVehiculeById(Long id){

        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Vehicule> retrieveVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule);
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule, Long idAgence, List<Long> idsEquipements) {
        if (vehiculeRepository.existsByImmatriculation(vehicule.getImmatriculation())) {
            throw new IllegalArgumentException("Immatriculation deja utilisee : " + vehicule.getImmatriculation());
        }

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new IllegalArgumentException("Agence introuvable : " + idAgence));
        vehicule.setAgence(agence);

        if (idsEquipements != null && !idsEquipements.isEmpty()) {
            List<Equipement> equipements = equipementRepository.findAllById(idsEquipements);
            if (equipements.size() != idsEquipements.size()) {
                throw new IllegalArgumentException("Un ou plusieurs equipements sont introuvables");
            }
            vehicule.setEquipements(new ArrayList<>(equipements));
        }

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    public Vehicule modifyVehicule(Vehicule vehicule, Long idAgence, List<Long> idsEquipements) {
        Vehicule existing = vehiculeRepository.findById(vehicule.getIdVehicule())
                .orElseThrow(() -> new IllegalArgumentException("Vehicule introuvable : " + vehicule.getIdVehicule()));

        if (!existing.getImmatriculation().equals(vehicule.getImmatriculation())
                && vehiculeRepository.existsByImmatriculation(vehicule.getImmatriculation())) {
            throw new IllegalArgumentException("Immatriculation deja utilisee : " + vehicule.getImmatriculation());
        }

        existing.setImmatriculation(vehicule.getImmatriculation());
        existing.setMarque(vehicule.getMarque());
        existing.setModele(vehicule.getModele());
        existing.setCategorie(vehicule.getCategorie());
        existing.setTarifJournalier(vehicule.getTarifJournalier());
        existing.setStatut(vehicule.getStatut());

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new IllegalArgumentException("Agence introuvable : " + idAgence));
        existing.setAgence(agence);

        if (idsEquipements != null) {
            if (idsEquipements.isEmpty()) {
                existing.setEquipements(new ArrayList<>());
            } else {
                List<Equipement> equipements = equipementRepository.findAllById(idsEquipements);
                if (equipements.size() != idsEquipements.size()) {
                    throw new IllegalArgumentException("Un ou plusieurs equipements sont introuvables");
                }
                existing.setEquipements(new ArrayList<>(equipements));
            }
        }

        return vehiculeRepository.save(existing);
    }
}
