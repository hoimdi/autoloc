package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Equipements", description = "Gestion des equipements AutoLoc")
@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;

    @Operation(summary = "Liste des equipements")
    @GetMapping
    public ResponseEntity<List<Equipement>> retrieveAllEquipements() {
        return ResponseEntity.ok(equipementService.retrieveAllEquipements());
    }

    @Operation(summary = "Recuperer un equipement par id")
    @GetMapping("/{idEquipement}")
    public ResponseEntity<Equipement> retrieveEquipement(@PathVariable Long idEquipement) {
        return equipementService.retrieveEquipement(idEquipement)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un equipement")
    @PostMapping
    public ResponseEntity<Equipement> addEquipement(@Valid @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.addEquipement(equipement));
    }

    @Operation(summary = "Modifier un equipement")
    @PutMapping
    public ResponseEntity<Equipement> modifyEquipement(@Valid @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.modifyEquipement(equipement));
    }

    @Operation(summary = "Supprimer un equipement")
    @DeleteMapping("/{idEquipement}")
    public ResponseEntity<Void> removeEquipement(@PathVariable Long idEquipement) {
        equipementService.removeEquipement(idEquipement);
        return ResponseEntity.noContent().build();
    }
}
