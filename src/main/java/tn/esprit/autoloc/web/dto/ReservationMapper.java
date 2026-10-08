package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "idClient", source = "client.idClient")
    @Mapping(target = "nomClient", source = "client.nom")
    @Mapping(target = "prenomClient", source = "client.prenom")
    @Mapping(target = "idVehicule", source = "vehicule.idVehicule")
    @Mapping(target = "immatriculationVehicule", source = "vehicule.immatriculation")
    @Mapping(target = "marqueVehicule", source = "vehicule.marque")
    @Mapping(target = "modeleVehicule", source = "vehicule.modele")
    ReservationResponseDTO toDto(Reservation reservation);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    Reservation toEntity(ReservationRequestDTO dto);
}
