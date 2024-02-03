package org.example.place;

import org.example.entity.device.Device;
import org.example.entity.residents.Resident;
import org.example.entity.item.Item;
import org.example.entity.sensor.InternalSensor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Room {
    private static final Logger log = Logger.getLogger(Room.class.getName());
    private final String name;
    private final List<Resident> residents = new ArrayList<>();
    private final List<Device> devices = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<InternalSensor> sensors = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public void addResident(Resident resident) {
        this.residents.add(resident);
    }
    public void addDevice(Device device) {
        this.devices.add(device);
    }

   public void addItem(Item item) {
      this.items.add(item);
   }

    public void addSensor(InternalSensor sensor) {    this.sensors.add(sensor);
    }

    public String getName() {
        return name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<Item> getItems() {
        return items;
    }

}
