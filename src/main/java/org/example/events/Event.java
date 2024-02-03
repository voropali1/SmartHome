package org.example.events;

import org.example.place.Room;

public abstract class Event {
    private final Source source;
    private final Room room;

    public Event(Source source, Room room) {
        this.source = source;
        this.room = room;
    }

    public Source getSource() {
        return source;
    }

    public Room getRoom() {
        return room;
    }

}
