package com.veronika.buildings.repositories;

import com.veronika.buildings.model.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
}
