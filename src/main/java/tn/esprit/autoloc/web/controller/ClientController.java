package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Clients", description = "Gestion des clients AutoLoc")
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Liste des clients")
    @GetMapping
    public ResponseEntity<List<Client>> retrieveAllClients() {
        return ResponseEntity.ok(clientService.retrieveAllClients());
    }

    @Operation(summary = "Recuperer un client par id")
    @GetMapping("/{idClient}")
    public ResponseEntity<Client> retrieveClient(@PathVariable Long idClient) {
        return clientService.retrieveClient(idClient)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recuperer un client par email")
    @GetMapping("/email/{email}")
    public ResponseEntity<Client> retrieveClientByEmail(@PathVariable String email) {
        return clientService.retrieveClientByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un client")
    @PostMapping
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
        return ResponseEntity.ok(clientService.addClient(client));
    }

    @Operation(summary = "Modifier un client")
    @PutMapping
    public ResponseEntity<Client> modifyClient(@Valid @RequestBody Client client) {
        return ResponseEntity.ok(clientService.modifyClient(client));
    }

    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{idClient}")
    public ResponseEntity<Void> removeClient(@PathVariable Long idClient) {
        clientService.removeClient(idClient);
        return ResponseEntity.noContent().build();
    }
}
