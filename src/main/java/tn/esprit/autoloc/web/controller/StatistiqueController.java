package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Statistiques", description = "Tableau de bord AutoLoc")
@RestController
@RequestMapping("/api/statistiques")
@RequiredArgsConstructor
public class StatistiqueController {

    private final IVehiculeRepository vehiculeRepository;
    private final IClientRepository clientRepository;
    private final IReservationRepository reservationRepository;

    @Operation(summary = "Statistiques globales AutoLoc")
    @GetMapping
    public ResponseEntity<Map<String, Object>> statistiquesGenerales() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalVehicules", vehiculeRepository.count());
        stats.put("vehiculesDisponibles", vehiculeRepository.findByStatut(StatutVehicule.DISPONIBLE).size());
        stats.put("vehiculesLoues", vehiculeRepository.findByStatut(StatutVehicule.LOUE).size());
        stats.put("vehiculesEnMaintenance", vehiculeRepository.findByStatut(StatutVehicule.MAINTENANCE).size());
        stats.put("totalClients", clientRepository.count());
        stats.put("totalReservations", reservationRepository.count());
        stats.put("reservationsEnAttente", reservationRepository.findByStatut(StatutReservation.EN_ATTENTE).size());
        stats.put("reservationsConfirmees", reservationRepository.findByStatut(StatutReservation.CONFIRMEE).size());
        stats.put("reservationsAnnulees", reservationRepository.findByStatut(StatutReservation.ANNULEE).size());
        stats.put("reservationsTerminees", reservationRepository.findByStatut(StatutReservation.TERMINEE).size());
        stats.put("chiffreAffaires", BigDecimal.ZERO);
        return ResponseEntity.ok(stats);
    }
}
