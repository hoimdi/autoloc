package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Tag(name = "Contrats", description = "Gestion des contrats et paiements AutoLoc")
@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @Operation(summary = "Liste des contrats")
    @GetMapping
    public ResponseEntity<List<Contrat>> retrieveAllContrats() {
        return ResponseEntity.ok(contratService.retrieveAllContrats());
    }

    @Operation(summary = "Recuperer un contrat par id")
    @GetMapping("/{idContrat}")
    public ResponseEntity<Contrat> retrieveContrat(@PathVariable Long idContrat) {
        return contratService.retrieveContrat(idContrat)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un contrat")
    @PostMapping
    public ResponseEntity<Contrat> addContrat(@RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.addContrat(contrat));
    }

    @Operation(summary = "Affecter une reservation a un contrat")
    @PostMapping("/{idContrat}/reservation/{idReservation}")
    public ResponseEntity<Contrat> affecterReservation(
            @PathVariable Long idContrat,
            @PathVariable Long idReservation) {
        return ResponseEntity.ok(contratService.affecterReservation(idContrat, idReservation));
    }

    @Operation(summary = "Ajouter un paiement a un contrat")
    @PostMapping("/{idContrat}/paiements")
    public ResponseEntity<Paiement> ajouterPaiement(
            @PathVariable Long idContrat,
            @RequestBody Paiement paiement) {
        return ResponseEntity.ok(contratService.ajouterPaiement(idContrat, paiement));
    }

    @Operation(summary = "Modifier un contrat")
    @PutMapping
    public ResponseEntity<Contrat> modifyContrat(@RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.modifyContrat(contrat));
    }

    @Operation(summary = "Supprimer un contrat (supprime aussi ses paiements en cascade)")
    @DeleteMapping("/{idContrat}")
    public ResponseEntity<Void> removeContrat(@PathVariable Long idContrat) {
        contratService.removeContrat(idContrat);
        return ResponseEntity.noContent().build();
    }
}
