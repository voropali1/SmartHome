package org.example.entity.sensor;


import org.example.place.Room;

import java.util.List;

public interface SensorFactory {

    Sensor create(String type, Room room);

    List<Sensor> getSensors();
}
