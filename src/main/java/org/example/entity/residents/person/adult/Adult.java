package org.example.entity.residents.person.adult;

import org.example.SmartHome.SmartHome;
import org.example.actions.Action;
import org.example.entity.Object;
import org.example.entity.device.Device;
import org.example.entity.device.Info;
import org.example.entity.item.Item;
import org.example.entity.residents.person.Person;
import org.example.entity.residents.person.child.Child;
import org.example.entity.residents.person.adult.state.AdultFixingState;
import org.example.entity.residents.person.adult.state.AdultOnState;
import org.example.entity.residents.person.adult.state.AdultWaitingState;
import org.example.entity.residents.pet.Pet;
import org.example.events.Event;
import org.example.events.EventsHandler;
import org.example.events.IsBroken;
import org.example.place.Room;

import java.util.logging.Logger;
public class Adult extends Person {
    private static final Logger log = Logger.getLogger(Adult.class.getName());

    public Adult(Room room, String name) {
        super(room, name);
        this.state = new AdultWaitingState(this);
        initEventHandlers();
    }
    public void feedChild(Child child) {
        if (!room.equals(child.getRoom())) {
            moveTo(child.getRoom());
        }
        log.info(String.format("Adult \"%s\" fed the child \"%s\" %s",
                name,
                child.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    public void changeDiapers(Child child) {
        if (!room.equals(child.getRoom())) {
            moveTo(child.getRoom());
        }
        log.info(String.format("Adult \"%s\" changed \"%s's\" diaper %s",
                name,
                child.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    public void feedPet(Pet pet) {
        if (!room.equals(pet.getRoom())) {
            moveTo(pet.getRoom());
        }
        log.info(String.format("Adult \"%s\" fed the pet \"%s\" %s",
                name,
                pet.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    public void fixDevice(Device device) {
        Info info = device.getInfo();
        object = device;
        changeState(new AdultFixingState(this));
        info.fixDevice(device);

    }

    public void useObject(Object object1) {
        this.object = object1;
        if (object instanceof Device) {
            SmartHome.getInstance().getReportSystem().getActivityAndUsageReport().deviceCount(this, (Device) object);
        } else {
            SmartHome.getInstance().getReportSystem().getActivityAndUsageReport().itemCount(this, (Item) object);
        }
        changeState(new AdultOnState(this));
    }
    public void afterUseObject(Object object) {
        this.object = object;
        changeState(new AdultWaitingState(this));
    }

    @Override
    public void update(long time) {
        this.state.update(time);
    }

    private void initEventHandlers() {
        SmartHome.getInstance().getEventDispatcher().addEventHandler(IsBroken.class, "global", new EventsHandler() {
            @Override
            public void handle(Event e) {
                if (Adult.this.state instanceof AdultWaitingState) {
                    if (!Adult.this.getRoom().equals(e.getRoom())) {
                        Adult.this.moveTo((Room) e.getRoom());
                    }
                    ((Device) e.getSource()).fix(Adult.this);
                }
                if (nextHandler != null) {
                    nextHandler.handle(e);
                } else {
                    SmartHome.getInstance().getTaskSystem().addTask(new Action(e));
                }
            }
        });

    }
}