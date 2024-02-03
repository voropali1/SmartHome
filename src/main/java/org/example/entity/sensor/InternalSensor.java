package org.example.entity.sensor;


import org.example.place.Room;

public abstract class InternalSensor extends Sensor {
    protected Room room;
    public InternalSensor(Room room) {
        this.room = room;
    }
    public Room getRoom() {
        return room;
    }
    public abstract void switchState();
    public abstract void resetState();
}
