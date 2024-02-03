package org.example.entity.residents.person;

import org.example.entity.residents.Resident;
import org.example.place.Room;

public abstract class Person extends Resident {
    public Person(Room room, String name) {
        super(room, name);
    }
}
