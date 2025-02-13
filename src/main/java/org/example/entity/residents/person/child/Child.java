package org.example.entity.residents.person.child;

import org.example.SmartHome.SmartHome;
import org.example.entity.residents.person.Person;
import org.example.entity.residents.person.adult.Adult;
import org.example.entity.residents.person.child.state.ChildAwakeState;
import org.example.entity.residents.person.child.state.ChildSleepingState;
//import org.example.entity.residents.person.person.Adult;
import org.example.events.HungryChild;
import org.example.events.PoopedChild;
import org.example.place.Room;

import java.util.logging.Logger;

public class Child extends Person {
    private static final Logger log = Logger.getLogger(Child.class.getName());

    private final double min_trigger_time = 2;
    private final double max_trigger_time = 30;

    private long timeFromLastDispatchedHungerEvent = 0;
    private long timeFromLastDispatchedPoopedEvent = 0;
    private boolean dispatchedHungerEvent = false;
    private boolean dispatchedPoopedEvent = false;
    private double triggeredHungerTime;
    private double triggeredPoopedTime;

    public Child(Room room, String name) {
        super(room, name);
        this.state = new ChildAwakeState(this);
        this.triggeredHungerTime = calculateTriggeredTime();
        this.triggeredPoopedTime = calculateTriggeredTime();
        this.deviceBreakingChance = 0.4;
    }

    private double calculateTriggeredTime() {
        return Math.random() * (max_trigger_time - min_trigger_time + 1) + min_trigger_time;
    }

    public void feed(Adult adult) {
        adult.feedChild(this);
        dispatchedHungerEvent = false;
    }

    public void changeDiaper(Adult adult) {
        adult.changeDiapers(this);
        dispatchedPoopedEvent = false;
    }


    public boolean getDispatchedPoopedEvent() {
        return dispatchedPoopedEvent;
    }
    public boolean getDispatchedHungerEvent() {
        return dispatchedHungerEvent;
    }
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedHungerEvent += time;
        this.timeFromLastDispatchedPoopedEvent += time;

        if (!dispatchedHungerEvent && timeFromLastDispatchedHungerEvent >= triggeredHungerTime * 3600 * 1000000000) {
            dispatchedHungerEvent = true;
            timeFromLastDispatchedHungerEvent = 0;
            triggeredHungerTime = calculateTriggeredTime();
            changeState(new ChildAwakeState(this));
            SmartHome.getInstance().getEventDispatcher().dispatchEvent(new HungryChild(this, room), "global");
        }
        if (!dispatchedPoopedEvent && timeFromLastDispatchedPoopedEvent >= triggeredPoopedTime * 3600 * 1000000000) {
            dispatchedPoopedEvent = true;
            timeFromLastDispatchedPoopedEvent = 0;
            triggeredPoopedTime = calculateTriggeredTime();
            changeState(new ChildSleepingState(this));
            SmartHome.getInstance().getEventDispatcher().dispatchEvent(new PoopedChild(this, room), "global");
        }
        this.state.update(time);
    }
}
