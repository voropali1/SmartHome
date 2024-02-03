package org.example.place;

import java.util.ArrayList;
import java.util.List;

public class House {
    private final List<Floor> floors = new ArrayList<>();

    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }

    public String getName() {
        return "House";
    }

    public List<Floor> getFloors() {
        return floors;
    }
}

