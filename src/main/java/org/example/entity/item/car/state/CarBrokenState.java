package org.example.entity.item.car.state;


import org.example.SmartHome.SmartHome;

import org.example.entity.device.light.state.LightFixingState;
import org.example.entity.item.Item;
import org.example.entity.item.state.BrokenState;
import org.example.events.IsBroken;

import java.util.logging.Logger;

public class CarBrokenState extends BrokenState {
    private static final Logger log = Logger.getLogger(CarBrokenState.class.getName());
    public CarBrokenState(Item item) {
        super(item);
        SmartHome.getInstance().getEventDispatcher().dispatchEvent(new IsBroken(item, item.getRoom()), "global");
        log.info(String.format("Car in room \"%s\" is broken %s",
                item.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        if (item.getTime() > item.getWorkingTime() * 3600L * 1000000000L) {
            item.changeState(new CarFixingState(item));
        }
        this.time += time;
        item.setTime(item.getTime() + time);
    }
}
