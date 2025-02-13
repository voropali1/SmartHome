package org.example.entity.residents.pet;

import org.example.SmartHome.SmartHome;
//import org.example.entity.residents.person.person.Adult;
import org.example.entity.residents.person.adult.Adult;
import org.example.entity.residents.pet.state.PetAwakeState;
import org.example.events.HungryPet;
import org.example.place.Room;

public class Bear extends Pet {
    private final double min_trigger_time = 3;
    private final double max_trigger_time = 20;
    private long timeFromLastDispatchedHungerEvent = 0;
    private boolean dispatchedHungerEvent = false;
    private double triggeredHungerTime;


    public Bear(Room room, String name) {
        super(room, name);
        this.triggeredHungerTime = calculateTriggeredTime();
    }
    private double calculateTriggeredTime() {
        return Math.random() * (max_trigger_time - min_trigger_time + 1) + min_trigger_time;
    }
    public void feed(Adult adult) {
        adult.feedPet(this);
        dispatchedHungerEvent = false;
    }
    public boolean getDispatchedHungerEvent() {
        return dispatchedHungerEvent;
    }

    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedHungerEvent += time;
        if (!dispatchedHungerEvent && timeFromLastDispatchedHungerEvent >= triggeredHungerTime * 3600 * 1000000000) {
            dispatchedHungerEvent = true;
            timeFromLastDispatchedHungerEvent = 0;
            triggeredHungerTime = calculateTriggeredTime();
            changeState(new PetAwakeState(this));
            SmartHome.getInstance().getEventDispatcher().dispatchEvent(new HungryPet(this, room), "global");
        }
        state.update(time);
    }
}