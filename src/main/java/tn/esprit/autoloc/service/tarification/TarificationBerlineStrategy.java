package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.CategorieVehicule;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TarificationBerlineStrategy implements TarificationStrategy {

    private static final BigDecimal COEFFICIENT = new BigDecimal("1.25");

    @Override
    public boolean support(CategorieVehicule categorie) {
        return CategorieVehicule.BERLINE.equals(categorie);
    }

    @Override
    public BigDecimal calculer(BigDecimal tarifBase) {
        return tarifBase.multiply(COEFFICIENT).setScale(2, RoundingMode.HALF_UP);
    }
}
