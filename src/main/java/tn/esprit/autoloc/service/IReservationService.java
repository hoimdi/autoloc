package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    List<Reservation> retrieveAllReservations();

    Optional<Reservation> retrieveReservation(Long idReservation);

    Reservation addReservation(Reservation reservation);

    void removeReservation(Long idReservation);

    Reservation modifyReservation(Reservation reservation);

    Reservation affecterClientEtVehicule(Long idReservation, Long idClient, Long idVehicule);
}
