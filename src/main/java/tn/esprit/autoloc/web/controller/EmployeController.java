package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Employes", description = "Gestion du personnel AutoLoc")
@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @Operation(summary = "Liste des employes")
    @GetMapping
    public ResponseEntity<List<Employe>> retrieveAllEmployes() {
        return ResponseEntity.ok(employeService.retrieveAllEmployes());
    }

    @Operation(summary = "Recuperer un employe par id")
    @GetMapping("/{idEmploye}")
    public ResponseEntity<Employe> retrieveEmploye(@PathVariable Long idEmploye) {
        return employeService.retrieveEmploye(idEmploye)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un employe")
    @PostMapping
    public ResponseEntity<Employe> addEmploye(@Valid @RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.addEmploye(employe));
    }

    @Operation(summary = "Modifier un employe")
    @PutMapping
    public ResponseEntity<Employe> modifyEmploye(@Valid @RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.modifyEmploye(employe));
    }

    @Operation(summary = "Supprimer un employe")
    @DeleteMapping("/{idEmploye}")
    public ResponseEntity<Void> removeEmploye(@PathVariable Long idEmploye) {
        employeService.removeEmploye(idEmploye);
        return ResponseEntity.noContent().build();
    }
}
