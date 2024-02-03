package org.example.entity.sensor;



import org.example.place.Room;

import java.util.ArrayList;
import java.util.List;
public class InternalSensorFactory implements SensorFactory {
    private static InternalSensorFactory instance;
    private final List<Sensor> sensors = new ArrayList<>();
    private InternalSensorFactory() {}

    public static InternalSensorFactory getInstance() {
        if (instance == null) {
            instance = new InternalSensorFactory();
        }

        return instance;
    }

    public Sensor create(String type, Room room) {
        Sensor sensor = switch (type) {
            case "TEMPERATURE" -> new TemperatureSensor((Room) room);
            case "LIGHT" -> new LightSensor((Room) room);
            default -> throw new IllegalArgumentException("Type of sensor " + type + " was not found.");
        };
        sensors.add(sensor);
        return sensor;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }
}
