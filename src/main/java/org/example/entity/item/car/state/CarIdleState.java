package org.example.entity.item.car.state;

import org.example.SmartHome.SmartHome;
import org.example.entity.item.Item;
import org.example.entity.item.state.IdleState;


import java.util.logging.Logger;
public class CarIdleState extends IdleState {
    private static final Logger log = Logger.getLogger(CarIdleState.class.getName());

    public CarIdleState(Item item) {
        super(item);
        log.info(String.format("Car in \"%s\" is not working %s",
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

