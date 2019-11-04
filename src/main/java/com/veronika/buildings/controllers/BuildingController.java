package com.veronika.buildings.controllers;

import static com.veronika.buildings.converters.TaxRateConverter.propertTaxParamsToTotalTaxRate;

import com.veronika.buildings.daos.BuildingDao;
import com.veronika.buildings.exceptions.BuildingNotFoundException;
import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;
import com.veronika.buildings.repositories.BuildingRepository;
import java.util.List;
import javax.validation.constraints.NotNull;
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
    private final BuildingDao buildingDao;

    public BuildingController(BuildingRepository buildingRepository, BuildingDao buildingDao) {
        this.buildingRepository = buildingRepository;
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<BuildingEntity> fetchAllBuildings() {
        return buildingRepository.findAll();
    }

    @PostMapping
    public BuildingEntity saveNewBuilding(@RequestBody BuildingEntity building) {
        return buildingRepository.save(building);
    }

    @GetMapping("/{id}")
    public BuildingEntity fetchBuildingById(@PathVariable Long id) {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @PutMapping("/{id}")
    public BuildingEntity updateBuildingOrCreateNew(@RequestBody BuildingEntity building, @PathVariable Long id) {

        return buildingRepository.findById(id)
                .map(b -> {
                    b.setOwner(building.getOwner());
                    b.setType(building.getType());
                    b.setValue(building.getValue());
                    b.setSize(building.getSize());
                    b.setAddress(building.getAddress());
                    return buildingRepository.save(building);
                })
                .orElseGet(() -> {
                    building.setId(id);
                    return buildingRepository.save(building);
                });
    }

    @GetMapping("/owner/{owner}")
    public List<BuildingEntity> fetchBuildingsByOwner(@PathVariable String owner) {
        return buildingDao.getBuildingsByOwner(owner);
    }

    @GetMapping("/owner/{owner}/tax")
    public double fetchTotalTaxByOwner(@PathVariable String owner) {
        return propertTaxParamsToTotalTaxRate(buildingDao.getBuildingsByOwner(owner));
    }

    @GetMapping("/similar")
    public List<BuildingEntity> fetchSimilarBuildinsOnStreet(String city, String street, PropertyType type, int size) {
        return buildingDao.getSimilarBuildings(city, street, type, size);
    }

}
