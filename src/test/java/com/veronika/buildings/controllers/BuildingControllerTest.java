package com.veronika.buildings.controllers;

import static com.veronika.buildings.test_utils.TestBuilding.building;
import static com.veronika.buildings.test_utils.TestBuildingsEntity.buildingEntity;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.veronika.buildings.exceptions.BuildingNotFoundException;
import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.repositories.BuildingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class BuildingControllerTest {

    @Mock
    BuildingRepository buildingRepository;
    private BuildingController buildingController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        buildingController = new BuildingController(buildingRepository);
    }

    @Test
    void fetchAllBuildings() {
        BuildingEntity buildingEntity = buildingEntity("veronika", 11L);
        BuildingEntity buildingEntity1 = buildingEntity("mindaugas", 10L);

        when(buildingRepository.findAll()).thenReturn(asList(buildingEntity, buildingEntity1));

        List<BuildingEntity> result = buildingController.fetchAllBuildings();

        assertEquals(buildingEntity.getOwner(), result.get(0).getOwner());
        assertEquals(buildingEntity1.getOwner(), result.get(1).getOwner());
        assertEquals(asList(buildingEntity, buildingEntity1).size(), result.size());
    }

    @Test
    void saveNewBuilding() {
        BuildingEntity buildingEntity = buildingEntity("veronika", 9L);
        when(buildingRepository.save(any(BuildingEntity.class))).thenReturn(buildingEntity);

        BuildingEntity result = buildingController.saveNewBuilding(building());

        assertEquals(buildingEntity.getOwner(), result.getOwner());
    }

    @Test
    void fetchBuildingById() {
        BuildingEntity buildingEntity = buildingEntity("tomas", 9L);
        when(buildingRepository.findById(9L)).thenReturn(Optional.of(buildingEntity));

        BuildingEntity result = buildingController.fetchBuildingById(9L);

        assertEquals(buildingEntity.getOwner(), result.getOwner());
    }

    @Test
    void fetchByInvalidId() {
        BuildingEntity buildingEntity = buildingEntity("tomas", 9L);
        when(buildingRepository.findById(9L)).thenReturn(Optional.of(buildingEntity));

        assertThrows(BuildingNotFoundException.class, () -> buildingController.fetchBuildingById(1L));
    }
}
