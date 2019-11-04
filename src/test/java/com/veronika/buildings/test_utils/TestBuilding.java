package com.veronika.buildings.test_utils;

import com.veronika.buildings.model.AddressEntity;
import com.veronika.buildings.model.Building;
import com.veronika.buildings.model.PropertyType;

public class TestBuilding {

    private TestBuilding() {
    }

    public static Building building() {
        return new Building("veronika", addressEntity(), 120_000.01, PropertyType.APARTMENT, 130);
    }

    private static AddressEntity addressEntity() {
        return new AddressEntity("vilnius", "tuju", 12);
    }
}
