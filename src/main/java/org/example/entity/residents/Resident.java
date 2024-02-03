package org.example.entity.residents;

import org.example.SmartHome.SmartHome;
import org.example.SmartHome.Time;
import org.example.entity.Object;
import org.example.events.Source;
import org.example.place.Room;

import java.util.logging.Logger;

public abstract class Resident implements Time, Source {
    private static final Logger log = Logger.getLogger(Resident.class.getName());

    protected final String name;
    protected Room room;
    protected ResidentState state;
    protected Object object = null;
    protected long time = 0;

    // Constants
    protected double deviceBreakingChance = 0;

    public Resident(Room room, String name) {
        this.room = room;
        this.name = name;


        SmartHome.getInstance().getSimulation().follow(this);
    }

    /**
     * Returns the room in which inhabitant is located
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Moves to another room
     * @param room the room
     */
    public void moveTo(Room room) {
        log.info(String.format("%s \"%s\" moved from room \"%s\" to room \"%s\" [%s]",
                this.getClass().getSimpleName(),
                this.getName(),
                this.getRoom().getName(),
                room.getName(),
                SmartHome.getInstance().getSimulation().gettime()));

        this.room = room;
    }

    /**
     * Returns the chance of breaking the device
     * @return chance
     */
    public double getDeviceBreakingChance() {
        return deviceBreakingChance;
    }

    /**
     * Returns the using object
     * @return device/item
     */
    public Object getObject() {
        return object;
    }

    /**
     * Changes the state
     * @param state the state to set
     */
    public void changeState(ResidentState state) {
        this.state = state;
    }

    /**
     * Returns the current state
     * @return state
     */
    public ResidentState getState() {
        return this.state;
    }
}// implements ITimeTracker, IEventSource
    // Logger
