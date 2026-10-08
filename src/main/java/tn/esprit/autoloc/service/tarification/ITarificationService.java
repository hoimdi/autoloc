package tn.esprit.autoloc.service.tarification;

import tn.esprit.autoloc.domain.CategorieVehicule;

import java.math.BigDecimal;

public interface ITarificationService {

    BigDecimal calculerTarifJournalier(CategorieVehicule categorie, BigDecimal tarifBase);
}
