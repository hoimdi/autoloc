package tn.esprit.autoloc.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContratEcheanceScheduler {

    private final IReservationRepository reservationRepository;
    private final IContratRepository contratRepository;

    @Scheduled(cron = "0 0 8 * * *")
    public void verifierEcheancesReservations() {
        log.info("Scheduler - Verification des echeances de reservations : {}", LocalDate.now());
        List<Reservation> toutes = reservationRepository.findAll();
        long echues = toutes.stream()
                .filter(r -> StatutReservation.CONFIRMEE.equals(r.getStatut()))
                .filter(r -> r.getDateFin() != null && r.getDateFin().isBefore(LocalDate.now()))
                .peek(r -> log.warn("Reservation #{} echue (fin prevue le {}) - statut={}",
                        r.getIdReservation(), r.getDateFin(), r.getStatut()))
                .count();
        log.info("Scheduler - {} reservations confirmees arrivees a terme.", echues);
    }

    @Scheduled(cron = "0 30 8 * * MON")
    public void statistiquesHebdomadairesContrats() {
        log.info("Scheduler - Rapport hebdomadaire contrats - total contrats : {}", contratRepository.count());
        List<Contrat> tous = contratRepository.findAll();
        long invalides = tous.stream().filter(c -> !c.isValide()).count();
        log.info("Scheduler - Contrats invalides ou en attente de validation : {}", invalides);
    }
}
