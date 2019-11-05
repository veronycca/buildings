package com.veronika.buildings.utils;

import static com.veronika.buildings.test_utils.TestBuilding.building;
import static com.veronika.buildings.test_utils.TestBuildingsEntity.addressEntity;
import static com.veronika.buildings.test_utils.TestBuildingsEntity.newBuildingEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.veronika.buildings.model.Building;
import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;
import org.junit.jupiter.api.Test;

class EntityOperatorTest {

    @Test
    void updateBuildingEntity() {
        BuildingEntity origingBuilding = newBuildingEntity("aw1", addressEntity("Kaunas", "Sodu", 1110), 120_000.01,
                PropertyType.APARTMENT, 1300);
        Building newBuilding = building();

        BuildingEntity result = EntityOperator.updateBuildingEntity(origingBuilding, newBuilding);

        assertEquals(newBuilding.getOwner(), result.getOwner());
        assertEquals(newBuilding.getSize(), result.getSize());
        assertEquals(newBuilding.getType(), result.getType());
        assertEquals(newBuilding.getValue(), result.getValue());
        assertEquals(newBuilding.getAddress().getCity(), result.getAddress().getCity());
    }

    @Test
    void toBuildingEntity() {
        BuildingEntity result = EntityOperator.toBuildingEntity(building());
        assertEquals(result.getOwner(), building().getOwner());
    }
}
