package org.example.entity.item.car.state;

import org.example.SmartHome.SmartHome;

import org.example.entity.item.Item;
import org.example.entity.item.state.OnState;

import java.util.logging.Logger;
public class CarOnState extends OnState {
    private static final Logger log = Logger.getLogger(CarOnState.class.getName());
    public CarOnState(Item item) {
        super(item);
        log.info(String.format("Car in room \"%s\" is working %s",
                item.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        if (item.getTime() > item.getWorkingTime() * 3600L * 1000000000L) {
            item.changeState(new CarBrokenState(item));
        }
        this.time += time;
        item.setTime(item.getTime() + time);
    }
}
