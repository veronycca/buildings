package com.veronika.buildings.daos;

import static com.veronika.buildings.test_utils.TestBuildingsEntity.addressEntity;
import static com.veronika.buildings.test_utils.TestBuildingsEntity.newBuildingEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;
import com.veronika.buildings.repositories.BuildingRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BuildingDaoTest {

    @Autowired
    BuildingRepository buildingRepository;

    @Test
    void testBuildingDaoMethods() {
        loadDB();
        getBuildingsByOwner();
        getSimilarBuildings();
    }

    private void getBuildingsByOwner() {
        List<BuildingEntity> buildingEntitiesByOwner = buildingRepository.getBuildingsByOwner("aw1");

        assertEquals(3, buildingEntitiesByOwner.size());
        assertEquals("aw1", buildingEntitiesByOwner.get(0).getOwner());
        assertEquals("aw1", buildingEntitiesByOwner.get(1).getOwner());
        assertEquals("aw1", buildingEntitiesByOwner.get(2).getOwner());
    }

    private void getSimilarBuildings() {
        List<BuildingEntity> similarBuildingEntities = buildingRepository
                .getSimilarBuildings("Vilnius", "Sodu", PropertyType.APARTMENT, 130);

        assertEquals(3, similarBuildingEntities.size());

        BuildingEntity expected1 = newBuildingEntity("aw1", addressEntity("Vilnius", "Sodu", 10), 120_000.01,
                PropertyType.APARTMENT, 130);
        BuildingEntity expected2 = newBuildingEntity("aw4", addressEntity("Vilnius", "Sodu", 13), 1_200_000.01,
                PropertyType.APARTMENT, 120);
        BuildingEntity expected3 = newBuildingEntity("aw2", addressEntity("Vilnius", "Sodu", 11), 120.01,
                PropertyType.APARTMENT, 140);

        assertEquals(expected1.getOwner(), similarBuildingEntities.get(0).getOwner());
        assertEquals(expected2.getOwner(), similarBuildingEntities.get(1).getOwner());
        assertEquals(expected3.getOwner(), similarBuildingEntities.get(2).getOwner());

        List<BuildingEntity> similarBuildingEntities2 = buildingRepository
                .getSimilarBuildings("Vilnius", "Sodu", PropertyType.INDUSTRIAL, 10);

        assertEquals(1, similarBuildingEntities2.size());
    }

    private void loadDB() {
        buildingRepository
                .save(newBuildingEntity("aw1", addressEntity("Vilnius", "Sodu", 10), 120_000.01, PropertyType.APARTMENT,
                        130));
        buildingRepository
                .save(newBuildingEntity("aw2", addressEntity("Vilnius", "Sodu", 11), 120.01, PropertyType.APARTMENT,
                        140));
        buildingRepository
                .save(newBuildingEntity("aw3", addressEntity("Vilnius", "Sodu", 12), 12.01, PropertyType.APARTMENT,
                        120));
        buildingRepository
                .save(newBuildingEntity("aw4", addressEntity("Vilnius", "Sodu", 13), 1_200_000.01, PropertyType.APARTMENT,
                        120));
        buildingRepository
                .save(newBuildingEntity("aw5", addressEntity("Vilnius", "Tuju", 14), 12.01, PropertyType.APARTMENT,
                        130));
        buildingRepository
                .save(newBuildingEntity("aw6", addressEntity("Kaunas", "Sodu", 15), 12.01, PropertyType.APARTMENT,
                        130));
        buildingRepository
                .save(newBuildingEntity("aw7", addressEntity("Vilnius", "Sodu", 16), 12.01, PropertyType.APARTMENT,
                        180));
        buildingRepository
                .save(newBuildingEntity("aw1", addressEntity("Vilnius", "Sodu", 17), 12.01, PropertyType.HOUSE, 130));
        buildingRepository
                .save(newBuildingEntity("aw1", addressEntity("Vilnius", "Sodu", 18), 12.01, PropertyType.INDUSTRIAL,
                        130));
    }
}