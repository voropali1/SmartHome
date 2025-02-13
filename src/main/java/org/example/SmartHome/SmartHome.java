package org.example.SmartHome;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.example.actions.ActionSystem;
import org.example.entity.device.Device;
import org.example.events.EventDispatcher;
import org.example.place.FloorBuilder;
import org.example.place.House;
import org.example.place.HouseBuilder;
import org.example.place.RoomBuilder;
import org.example.reports.ReportSystem;
import org.json.JSONArray;
import org.json.JSONObject;


public class SmartHome {

    private List<Device> devices;
    private static SmartHome instance;
    private static JSONObject deviceObject;
    private static JSONObject jo;
    private EventDispatcher eventDispatcher;
    private Simulation simulation;
    private ReportSystem report;
    private ActionSystem actions;
    private House house;

    public SmartHome() {
        devices = new ArrayList<>();
        this.simulation = new Simulation();
        this.eventDispatcher = new EventDispatcher();
        this.actions = new ActionSystem();
    }

    public House loadDevices(String path) {
        this.report = new ReportSystem();
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HouseBuilder houseBuilder = new HouseBuilder();

        // Floors
        JSONArray floors = null;
        try {
            floors = jo.getJSONArray("floors");
        } catch (Exception ignored) {
        }
        if (floors != null) {
            for (Object floorObject : floors) {
                JSONObject floor = (JSONObject) floorObject;
                FloorBuilder floorBuilder = houseBuilder.addFloor(floor.getInt("level"));
                // Rooms
                JSONArray rooms = null;
                try {
                    rooms = floor.getJSONArray("rooms");
                } catch (Exception ignored) {
                }
                if (rooms != null) {
                    for (Object roomObject : rooms) {
                        JSONObject room = (JSONObject) roomObject;
                        RoomBuilder roomBuilder = floorBuilder.addRoom(room.getString("name"));

                        // Internal Sensors
                        JSONArray sensors = null;
                        try {
                            sensors = room.getJSONArray("sensors");
                        } catch (Exception ignored) {}
                        if (sensors != null) {
                            for (Object sensorObject : sensors) {
                                roomBuilder.addSensor(sensorObject.toString());
                            }
                        }
                        // Items
                        JSONArray items = null;
                        try {
                            items = room.getJSONArray("items");
                        } catch (Exception ignored) {}
                        if (items != null) {
                            for (Object itemObject : items) {
                                JSONObject item = (JSONObject) itemObject;
                                roomBuilder.addItem(item.getString("type"), item.getString("name"));
                            }
                        }
                        // Devices
                        JSONArray devices = null;
                        try {
                            devices = room.getJSONArray("devices");
                        } catch (Exception ignored) {
                        }
                        if (devices != null) {
                            for (Object deviceObject : devices) {
                                JSONObject device = (JSONObject) deviceObject;
                                roomBuilder.addDevice(device.getString("type"), device.getString("name"));
                            }
                        }
                        // Persons
                        JSONArray persons = null;
                        try {
                            persons = room.getJSONArray("persons");
                        } catch (Exception ignored) {
                        }
                        if (persons != null) {
                            for (Object personObject : persons) {
                                JSONObject person = (JSONObject) personObject;
                                roomBuilder.addPerson(person.getString("type"), person.getString("name"));
                            }
                        }
                        // Pets
                        JSONArray pets = null;
                        try {
                            pets = room.getJSONArray("pets");
                        } catch (Exception ignored) {}
                        if (pets != null) {
                            for (Object petObject : pets) {
                                JSONObject pet = (JSONObject) petObject;
                                roomBuilder.addPet(pet.getString("type"), pet.getString("name"));
                            }
                        }
                    }
                }
            }
        }
        house = houseBuilder.getResult();
                return houseBuilder.getResult();

    }

    public static SmartHome getInstance () {
        if (instance == null) {
            instance = new SmartHome();
        }
        return instance;
    }

    public ReportSystem getReportSystem() {
       return report;
    }
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void start(int speed, long timeToSimulate){
        simulation.start(speed, timeToSimulate);
    }

    public ActionSystem getTaskSystem() {
        return actions;
    }

    public House getHouse() {
        return house;
    }

}