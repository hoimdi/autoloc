package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;
import tn.esprit.autoloc.web.dto.VehiculeMapper;

import java.util.List;

@Tag(name = "Vehicules", description = "Gestion des vehicules AutoLoc")
@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    @Operation(summary = "Liste des vehicules")
    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> retrieveAllVehicules() {
        List<VehiculeDTO> dtos = vehiculeService.retrieveAllVehicules()
                .stream()
                .map(vehiculeMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un vehicule par son id")
    @GetMapping("/{idVehicule}")
    public ResponseEntity<VehiculeDTO> retrieveVehicule(@PathVariable Long idVehicule) {
        return vehiculeService.retrieveVehicule(idVehicule)
                .map(vehiculeMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un vehicule")
    @PostMapping
    public ResponseEntity<VehiculeDTO> addVehicule(@Valid @RequestBody VehiculeDTO dto) {
        Vehicule entity = vehiculeMapper.toEntity(dto);
        Vehicule saved = vehiculeService.addVehicule(entity);
        return ResponseEntity.ok(vehiculeMapper.toDto(saved));
    }

    @Operation(summary = "Modifier un vehicule")
    @PutMapping
    public ResponseEntity<VehiculeDTO> modifyVehicule(@Valid @RequestBody VehiculeDTO dto) {
        Vehicule entity = vehiculeMapper.toEntity(dto);
        Vehicule saved = vehiculeService.modifyVehicule(entity);
        return ResponseEntity.ok(vehiculeMapper.toDto(saved));
    }

    @Operation(summary = "Supprimer un vehicule")
    @DeleteMapping("/{idVehicule}")
    public ResponseEntity<Void> removeVehicule(@PathVariable Long idVehicule) {
        vehiculeService.removeVehicule(idVehicule);
        return ResponseEntity.noContent().build();
    }
}
