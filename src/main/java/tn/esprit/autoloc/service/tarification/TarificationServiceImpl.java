package tn.esprit.autoloc.service.tarification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.CategorieVehicule;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarificationServiceImpl implements ITarificationService {

    private final List<TarificationStrategy> strategies;

    @Override
    public BigDecimal calculerTarifJournalier(CategorieVehicule categorie, BigDecimal tarifBase) {
        return strategies.stream()
                .filter(s -> s.support(categorie))
                .findFirst()
                .map(s -> s.calculer(tarifBase))
                .orElse(tarifBase);
    }
}
