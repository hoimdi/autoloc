package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;
import java.util.Optional;

public interface IEmployeService {

    List<Employe> retrieveAllEmployes();

    Optional<Employe> retrieveEmploye(Long idEmploye);

    Employe addEmploye(Employe employe);

    void removeEmploye(Long idEmploye);

    Employe modifyEmploye(Employe employe);
}
