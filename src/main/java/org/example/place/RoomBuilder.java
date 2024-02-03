package org.example.place;


import org.example.entity.device.DeviceFactory;
import org.example.entity.item.ItemFactory;
import org.example.entity.residents.person.PersonFactory;
import org.example.entity.residents.pet.PetFactory;
import org.example.entity.sensor.InternalSensor;
import org.example.entity.sensor.InternalSensorFactory;
import org.example.entity.sensor.SensorFactory;

public class RoomBuilder {
    private final Room room;
    private final FloorBuilder floorBuilder;
    private final Floor floor;

    public RoomBuilder(FloorBuilder floorBuilder, Floor floor, String name) {
        this.floorBuilder = floorBuilder;
        this.floor = floor;
        this.room = new Room(name);
        floor.addRoom(room);
    }

    public RoomBuilder addPerson(String type, String name) {
        PersonFactory personFactory = PersonFactory.getInstance();
        room.addResident(personFactory.create(type, name, room));
        return this;
    }

    public RoomBuilder addPet(String type, String name) {
        PetFactory petFactory = PetFactory.getInstance();
        room.addResident(petFactory.create(type, name, room));
        return this;
    }

    public RoomBuilder addDevice(String type, String name) {
        DeviceFactory deviceFactory = DeviceFactory.getInstance();
        room.addDevice(deviceFactory.createDevice(type, room, name));
        return this;
    }

    public RoomBuilder addItem(String type, String name) {
        ItemFactory itemFactory = ItemFactory.getInstance();
        room.addItem(itemFactory.create(type, room, name));
        return this;
    }

    public RoomBuilder addSensor(String type) {
        SensorFactory sensorFactory = InternalSensorFactory.getInstance();
        room.addSensor((InternalSensor) sensorFactory.create(type, room));
        return this;
    }

    public FloorBuilder end() {
        return floorBuilder;
    }
}
