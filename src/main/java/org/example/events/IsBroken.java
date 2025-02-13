package org.example.events;

import org.example.SmartHome.SmartHome;
import org.example.place.Room;
import java.util.logging.Logger;

public class IsBroken extends Event {
    private static final Logger log = Logger.getLogger(IsBroken.class.getName());
    public IsBroken(Source source, Room room) {
        super(source, room);
       log.info(String.format("\"%s\" in room \"%s\": is broken %s",
               source.getName(),
               room.getName(),
               SmartHome.getInstance().getSimulation().gettime()));
    }
}