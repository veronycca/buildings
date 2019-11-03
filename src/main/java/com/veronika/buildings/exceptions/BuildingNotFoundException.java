package com.veronika.buildings.exceptions;

public class BuildingNotFoundException extends RuntimeException {

    public BuildingNotFoundException(Long id) {
        super("Building with id = " + id + " does not exist");
    }
}
