package com.veronika.buildings.utils;

import com.veronika.buildings.model.BuildingEntity;
import java.util.List;

public class TaxRateCalculator {

    private TaxRateCalculator() {
    }

    public static double propertyTaxParamsToTotalTaxRate(List<BuildingEntity> buildingEntities) {

        double totalTaxRate = buildingEntities.stream()
                .map(p -> p.getValue() * p.getType().getTaxRate())
                .reduce(0.0, Double::sum);

        return  (double) Math.round(totalTaxRate * 100.00) / 100.00;
    }
}
