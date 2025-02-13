package org.example.entity.residents.pet;

import org.example.entity.residents.Resident;
//import org.example.entity.residents.person.person.Adult;
import org.example.entity.residents.pet.state.PetAwakeState;
import org.example.place.Room;

import java.util.logging.Logger;

public abstract class Pet extends Resident {
    private static final Logger log = Logger.getLogger(Pet.class.getName());
    public Pet(Room room, String name) {
        super(room, name);
        this.state = new PetAwakeState(this);
        this.deviceBreakingChance = 0.3;
    }

    public abstract boolean getDispatchedHungerEvent();
}