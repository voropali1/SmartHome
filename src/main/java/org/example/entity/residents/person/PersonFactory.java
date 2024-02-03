package org.example.entity.residents.person;
import org.example.entity.residents.person.adult.Adult;
import org.example.entity.residents.person.child.Child;
import org.example.place.Room;

import java.util.ArrayList;
import java.util.List;

public class PersonFactory {
    private static PersonFactory instance;
    private final List<Person> persons = new ArrayList<>();

    private PersonFactory() {}

    public static PersonFactory getInstance() {
        if (instance == null) {
            instance = new PersonFactory();
        }
        return instance;
    }

    public Person create(String type, String name, Room room) {
        Person person = switch (type) {
            case "ADULT" -> new Adult((Room) room, name);
            case "CHILD" -> new Child((Room) room, name);
            default -> throw new IllegalArgumentException("Type of person " + type + " was not found.");
        };
        persons.add(person);
        return person;
    }

    public List<Person> getPersons() {
        return persons;
    }
}