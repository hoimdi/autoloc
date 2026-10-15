package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Agences", description = "Gestion des agences AutoLoc")
@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Liste des agences")
    @GetMapping
    public ResponseEntity<List<Agence>> retrieveAllAgences() {
        return ResponseEntity.ok(agenceService.retrieveAllAgences());
    }

    @Operation(summary = "Recuperer une agence par id")
    @GetMapping("/{idAgence}")
    public ResponseEntity<Agence> retrieveAgence(@PathVariable Long idAgence) {
        return agenceService.retrieveAgence(idAgence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter une agence")
    @PostMapping
    public ResponseEntity<Agence> addAgence(@Valid @RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.addAgence(agence));
    }

    @Operation(summary = "Modifier une agence")
    @PutMapping
    public ResponseEntity<Agence> modifyAgence(@Valid @RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.modifyAgence(agence));
    }

    @Operation(summary = "Supprimer une agence")
    @DeleteMapping("/{idAgence}")
    public ResponseEntity<Void> removeAgence(@PathVariable Long idAgence) {
        agenceService.removeAgence(idAgence);
        return ResponseEntity.noContent().build();
    }
}
