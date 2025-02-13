package org.example.entity.device;

import org.example.entity.device.airCondicioner.airConditioner;
import org.example.entity.device.light.Light;
import org.example.place.Room;

import java.util.ArrayList;
import java.util.List;

public class DeviceFactory {
    private static DeviceFactory instance;
    private  List<Device> devices = new ArrayList<>();

    public  Device createDevice(String type, Room room, String name) {
        Device device = switch (type) {
            case "LIGHT" -> new Light(room, name);
            case "AIRCONDICIONER" -> new airConditioner(room, name);
            default -> throw new IllegalArgumentException("Type of device " + type + " was not found.");
        };
        this.devices.add(device);
        return device;
    }

    public static DeviceFactory getInstance() {
        if (instance == null) {
            instance = new DeviceFactory();
        }
        return instance;
    }

    public List<Device> getDevices() {
        return this.devices;
    }
}
