package com.veronika.buildings.utils;

import static com.veronika.buildings.test_utils.TestBuildingsEntity.addressEntity;
import static com.veronika.buildings.test_utils.TestBuildingsEntity.newBuildingEntity;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;
import java.util.List;
import org.junit.jupiter.api.Test;

class TaxRateCalculatorTest {

    @Test
    void testPropertyTaxParamsToTotalTaxRate() {
        BuildingEntity result1 = newBuildingEntity("aw1", addressEntity("Vilnius", "Sodu", 10),
                120_000.01, PropertyType.APARTMENT, 130);
        BuildingEntity result2 = newBuildingEntity("aw1", addressEntity("Vilnius", "Sodu", 13),
                1_200_000.01, PropertyType.INDUSTRIAL, 120);
        BuildingEntity result3 = newBuildingEntity("aw1", addressEntity("Vilnius", "Sodu", 11),
                120.01, PropertyType.HOUSE, 140);
        List<BuildingEntity> buildingEntities = asList(result1, result2, result3);

        double result = TaxRateCalculator.propertyTaxParamsToTotalTaxRate(buildingEntities);

        assertEquals(37202.40, result);
    }
}