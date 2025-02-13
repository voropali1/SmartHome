package org.example.entity.residents.pet;

import org.example.place.Room;

import java.util.ArrayList;
import java.util.List;

public class PetFactory {
    private static PetFactory instance;
    private final List<Pet> pets = new ArrayList<>();
    private PetFactory() {}
    public static PetFactory getInstance() {
        if (instance == null) {
            instance = new PetFactory();
        }

        return instance;
    }
    public Pet create(String type, String name, Room room) {
        Pet pet = switch (type) {
            case "BEAR" -> new Bear(room, name);
            default -> throw new IllegalArgumentException("Type of pet " + type + " was not found.");
        };
        pets.add(pet);
        return pet;
    }

    public List<Pet> getPets() {
        return pets;
    }
}
