package org.example.place;

public class FloorBuilder {
    private final Floor floor;
    private final HouseBuilder houseBuilder;
    private final House house;

    public FloorBuilder(HouseBuilder houseBuilder, House house, int level) {
        this.houseBuilder = houseBuilder;
        this.house = house;
        this.floor = new Floor(level);

        house.addFloor(floor);
    }

    public RoomBuilder addRoom(String name) {
        return new RoomBuilder(this, floor, name);
    }

    public HouseBuilder end() {
        return houseBuilder;
    }
}
