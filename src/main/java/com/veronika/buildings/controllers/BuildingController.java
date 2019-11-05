package com.veronika.buildings.controllers;

import static com.veronika.buildings.utils.TaxRateCalculator.propertyTaxParamsToTotalTaxRate;
import static com.veronika.buildings.utils.EntityOperator.updateBuildingEntity;
import static com.veronika.buildings.utils.EntityOperator.toBuildingEntity;

import com.veronika.buildings.exceptions.BuildingNotFoundException;
import com.veronika.buildings.model.Building;
import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;
import com.veronika.buildings.repositories.BuildingRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/buildings")
public class BuildingController {

    private final BuildingRepository buildingRepository;

    public BuildingController(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @GetMapping
    public List<BuildingEntity> fetchAllBuildings() {
        return buildingRepository.findAll();
    }

    @PostMapping
    public BuildingEntity saveNewBuilding(@RequestBody Building building) {
        return buildingRepository.save(toBuildingEntity(building));
    }

    @GetMapping("/{id}")
    public BuildingEntity fetchBuildingById(@PathVariable Long id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @GetMapping("/owner/{owner}")
    public List<BuildingEntity> fetchBuildingsByOwner(@PathVariable String owner) {
        return buildingRepository.getBuildingsByOwner(owner);
    }

    @PutMapping("/{id}")
    public BuildingEntity updateBuildingById(@RequestBody Building building, @PathVariable Long id) {
        return buildingRepository.findById(id)
                .map(origin -> buildingRepository.save(updateBuildingEntity(origin, building)))
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @GetMapping("/owner/{owner}/tax")
    public double fetchTotalTaxByOwner(@PathVariable String owner) {
        return propertyTaxParamsToTotalTaxRate(buildingRepository.getBuildingsByOwner(owner));
    }

    @GetMapping("/similar")
    public List<BuildingEntity> fetchSimilarBuildinsOnStreet(String city, String street, PropertyType type, int size) {
        return buildingRepository.getSimilarBuildings(city, street, type, size);
    }

}
