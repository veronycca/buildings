package com.veronika.buildings.converters;

import com.veronika.buildings.model.BuildingEntity;
import java.util.List;

public class TaxRateConverter {

    private TaxRateConverter() {
    }

    public static double propertTaxParamsToTotalTaxRate(List<BuildingEntity> buildingEntities) {

        return buildingEntities.stream()
                .map(p -> p.getSize() * p.getType().getTaxRate())
                .reduce(0.0, Double::sum);
    }
}
