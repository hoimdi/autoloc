package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ReservationMapper;
import tn.esprit.autoloc.web.dto.ReservationRequestDTO;
import tn.esprit.autoloc.web.dto.ReservationResponseDTO;

import java.util.List;

@Tag(name = "Reservations", description = "Gestion des reservations AutoLoc")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final IClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;

    @Operation(summary = "Liste des reservations")
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> retrieveAllReservations() {
        List<ReservationResponseDTO> dtos = reservationService.retrieveAllReservations()
                .stream()
                .map(reservationMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer une reservation par id")
    @GetMapping("/{idReservation}")
    public ResponseEntity<ReservationResponseDTO> retrieveReservation(@PathVariable Long idReservation) {
        return reservationService.retrieveReservation(idReservation)
                .map(reservationMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter une reservation")
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> addReservation(@Valid @RequestBody ReservationRequestDTO dto) {
        Reservation entity = reservationMapper.toEntity(dto);
        if (dto.getIdClient() != null) {
            clientRepository.findById(dto.getIdClient()).ifPresent(entity::setClient);
        }
        if (dto.getIdVehicule() != null) {
            vehiculeRepository.findById(dto.getIdVehicule()).ifPresent(entity::setVehicule);
        }
        Reservation saved = reservationService.addReservation(entity);
        return ResponseEntity.ok(reservationMapper.toDto(saved));
    }

    @Operation(summary = "Affecter client et vehicule a une reservation")
    @PostMapping("/{idReservation}/affecter/{idClient}/{idVehicule}")
    public ResponseEntity<ReservationResponseDTO> affecterClientVehicule(
            @PathVariable Long idReservation,
            @PathVariable Long idClient,
            @PathVariable Long idVehicule) {
        Reservation saved = reservationService.affecterClientEtVehicule(idReservation, idClient, idVehicule);
        return ResponseEntity.ok(reservationMapper.toDto(saved));
    }

    @Operation(summary = "Modifier une reservation")
    @PutMapping
    public ResponseEntity<Reservation> modifyReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.modifyReservation(reservation));
    }

    @Operation(summary = "Supprimer une reservation")
    @DeleteMapping("/{idReservation}")
    public ResponseEntity<Void> removeReservation(@PathVariable Long idReservation) {
        reservationService.removeReservation(idReservation);
        return ResponseEntity.noContent().build();
    }
}
