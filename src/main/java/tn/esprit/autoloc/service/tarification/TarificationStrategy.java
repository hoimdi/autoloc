package tn.esprit.autoloc.service.tarification;

import tn.esprit.autoloc.domain.CategorieVehicule;

import java.math.BigDecimal;

public interface TarificationStrategy {

    boolean support(CategorieVehicule categorie);

    BigDecimal calculer(BigDecimal tarifBase);
}
