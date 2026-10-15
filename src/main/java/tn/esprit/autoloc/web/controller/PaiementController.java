package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Paiements", description = "Gestion des paiements AutoLoc")
@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    @Operation(summary = "Liste des paiements")
    @GetMapping
    public ResponseEntity<List<Paiement>> retrieveAllPaiements() {
        return ResponseEntity.ok(paiementService.retrieveAllPaiements());
    }

    @Operation(summary = "Recuperer un paiement par id")
    @GetMapping("/{idPaiement}")
    public ResponseEntity<Paiement> retrievePaiement(@PathVariable Long idPaiement) {
        return paiementService.retrievePaiement(idPaiement)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un paiement")
    @PostMapping
    public ResponseEntity<Paiement> addPaiement(@Valid @RequestBody Paiement paiement) {
        return ResponseEntity.ok(paiementService.addPaiement(paiement));
    }

    @Operation(summary = "Modifier un paiement")
    @PutMapping
    public ResponseEntity<Paiement> modifyPaiement(@Valid @RequestBody Paiement paiement) {
        return ResponseEntity.ok(paiementService.modifyPaiement(paiement));
    }

    @Operation(summary = "Supprimer un paiement")
    @DeleteMapping("/{idPaiement}")
    public ResponseEntity<Void> removePaiement(@PathVariable Long idPaiement) {
        paiementService.removePaiement(idPaiement);
        return ResponseEntity.noContent().build();
    }
}
