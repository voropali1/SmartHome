package org.example.entity.item.car.state;


import org.example.SmartHome.SmartHome;
import org.example.entity.item.Item;
import org.example.entity.item.state.FixingState;


import java.util.logging.Logger;

public class CarFixingState extends FixingState {
    private static final Logger log = Logger.getLogger(CarFixingState.class.getName());

    public CarFixingState(Item item) {
        super(item);
        log.info(String.format("Car in room \"%s\" is being fixed %s",
                item.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        this.time += time;
    }
}
