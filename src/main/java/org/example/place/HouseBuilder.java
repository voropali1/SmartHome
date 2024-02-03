package org.example.place;

public class HouseBuilder {
    private final House house = new House();

    public FloorBuilder addFloor(int level) {
        return new FloorBuilder(this, house, level);
    }

    public House getResult() {
        return this.house;
    }
}
