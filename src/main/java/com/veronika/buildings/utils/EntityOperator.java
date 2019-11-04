package com.veronika.buildings.utils;

import com.veronika.buildings.model.Building;
import com.veronika.buildings.model.BuildingEntity;

public class EntityOperator {

    private EntityOperator() {
    }

    public static BuildingEntity updateBuildingEntity(BuildingEntity originBuildingEntity, Building newBuildingEntity) {
        originBuildingEntity.setOwner(newBuildingEntity.getOwner());
        originBuildingEntity.setAddress(newBuildingEntity.getAddress());
        originBuildingEntity.setType(newBuildingEntity.getType());
        originBuildingEntity.setValue(newBuildingEntity.getValue());
        originBuildingEntity.setSize(newBuildingEntity.getSize());
        return originBuildingEntity;
    }

    public static BuildingEntity toBuildingEntity(Building building) {

        return new BuildingEntity(building.getOwner(), building.getAddress(), building.getValue(), building.getType(),
                building.getSize());
    }
}
