package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Agences & Employes", description = "Gestion des agences et du personnel AutoLoc")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AgenceEmployeController {

    private final IAgenceService agenceService;
    private final IEmployeService employeService;

    @Operation(summary = "Liste des agences")
    @GetMapping("/agences")
    public ResponseEntity<List<Agence>> retrieveAllAgences() {
        return ResponseEntity.ok(agenceService.retrieveAllAgences());
    }

    @Operation(summary = "Recuperer une agence")
    @GetMapping("/agences/{idAgence}")
    public ResponseEntity<Agence> retrieveAgence(@PathVariable Long idAgence) {
        return agenceService.retrieveAgence(idAgence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter une agence")
    @PostMapping("/agences")
    public ResponseEntity<Agence> addAgence(@RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.addAgence(agence));
    }

    @Operation(summary = "Liste des employes")
    @GetMapping("/employes")
    public ResponseEntity<List<Employe>> retrieveAllEmployes() {
        return ResponseEntity.ok(employeService.retrieveAllEmployes());
    }

    @Operation(summary = "Ajouter un employe")
    @PostMapping("/employes")
    public ResponseEntity<Employe> addEmploye(@RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.addEmploye(employe));
    }
}
