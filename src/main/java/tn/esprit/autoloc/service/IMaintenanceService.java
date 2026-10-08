package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;
import java.util.Optional;

public interface IMaintenanceService {

    List<Maintenance> retrieveAllMaintenances();

    Optional<Maintenance> retrieveMaintenance(Long idMaintenance);

    Maintenance addMaintenance(Maintenance maintenance);

    void removeMaintenance(Long idMaintenance);

    Maintenance modifyMaintenance(Maintenance maintenance);
}
