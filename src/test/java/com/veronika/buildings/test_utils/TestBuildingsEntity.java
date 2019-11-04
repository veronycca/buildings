package com.veronika.buildings.test_utils;

import com.veronika.buildings.model.AddressEntity;
import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;

public class TestBuildingsEntity {

    private TestBuildingsEntity() {
    }

    public static BuildingEntity buildingEntity(String owner, Long id) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(id);
        buildingEntity.setAddress(addressEntity("vilnius", "tuju", 12));
        buildingEntity.setValue(120_000.01);
        buildingEntity.setSize(130);
        buildingEntity.setType(PropertyType.APARTMENT);
        buildingEntity.setOwner(owner);
        return new BuildingEntity();
    }

    public static AddressEntity addressEntity(String city, String street, int number) {
        return new AddressEntity(city, street, number);
    }

    public static BuildingEntity newBuildingEntity(String owner, AddressEntity addressEntity,
            double value, PropertyType propertyType, int size) {
        return new BuildingEntity(owner, addressEntity, value, propertyType, size);
    }

}
