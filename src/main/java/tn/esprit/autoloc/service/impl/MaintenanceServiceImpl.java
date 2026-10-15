package tn.esprit.autoloc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final IVehiculeRepository vehiculeRepository;

    @Override
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Optional<Maintenance> retrieveMaintenance(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance);
    }

    @Override
    public Maintenance addMaintenance(Maintenance maintenance) {
        affecterVehicule(maintenance);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void removeMaintenance(Long idMaintenance) {
        maintenanceRepository.deleteById(idMaintenance);
    }

    @Override
    public Maintenance modifyMaintenance(Maintenance maintenance) {
        affecterVehicule(maintenance);
        return maintenanceRepository.save(maintenance);
    }

    private void affecterVehicule(Maintenance maintenance) {
        if (maintenance.getVehicule() != null && maintenance.getVehicule().getIdVehicule() != null) {
            Vehicule vehiculeRef = vehiculeRepository.getReferenceById(maintenance.getVehicule().getIdVehicule());
            maintenance.setVehicule(vehiculeRef);
        }
    }
}
