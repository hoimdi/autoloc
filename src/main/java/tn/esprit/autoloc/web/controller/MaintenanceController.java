package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Maintenances", description = "Gestion des maintenances AutoLoc")
@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @Operation(summary = "Liste des maintenances")
    @GetMapping
    public ResponseEntity<List<Maintenance>> retrieveAllMaintenances() {
        return ResponseEntity.ok(maintenanceService.retrieveAllMaintenances());
    }

    @Operation(summary = "Recuperer une maintenance par id")
    @GetMapping("/{idMaintenance}")
    public ResponseEntity<Maintenance> retrieveMaintenance(@PathVariable Long idMaintenance) {
        return maintenanceService.retrieveMaintenance(idMaintenance)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter une maintenance")
    @PostMapping
    public ResponseEntity<Maintenance> addMaintenance(@Valid @RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.addMaintenance(maintenance));
    }

    @Operation(summary = "Modifier une maintenance")
    @PutMapping
    public ResponseEntity<Maintenance> modifyMaintenance(@Valid @RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.modifyMaintenance(maintenance));
    }

    @Operation(summary = "Supprimer une maintenance")
    @DeleteMapping("/{idMaintenance}")
    public ResponseEntity<Void> removeMaintenance(@PathVariable Long idMaintenance) {
        maintenanceService.removeMaintenance(idMaintenance);
        return ResponseEntity.noContent().build();
    }
}
