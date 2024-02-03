package org.example.events;

import org.example.SmartHome.SmartHome;
import org.example.place.Room;

import java.util.logging.Logger;

public class HungryPet extends Event {
    private static final Logger log = Logger.getLogger(HungryPet.class.getName());
    public HungryPet(Source source, Room room) {
        super(source, room);
        log.info(String.format("Pet \"%s\" in room \"%s\": \" is hungry\" %s",
                source.getName(),
                room.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
}
