package com.veronika.buildings.repositories;

import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    List<BuildingEntity> getBuildingsByOwner(String owner);
    List<BuildingEntity> getSimilarBuildings(String city, String street, PropertyType propertyType,
            int size);
}
