package org.example.events;

import org.example.SmartHome.SmartHome;
import org.example.place.Room;

import java.util.logging.Logger;

public class HungryChild extends Event {
    private static final Logger log = Logger.getLogger(HungryChild.class.getName());

    public HungryChild(Source source, Room room) {
        super(source, room);
        log.info(String.format("Child \"%s\" in room \"%s\": \" is hungry\" %s",
                source.getName(),
                room.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
}
