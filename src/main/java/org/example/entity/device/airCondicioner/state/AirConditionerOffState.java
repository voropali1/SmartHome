package org.example.entity.device.airCondicioner.state;
import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.OffState;

import java.util.logging.Logger;

public class AirConditionerOffState extends OffState {
    private static final Logger log = Logger.getLogger(AirConditionerOffState.class.getName());
    public AirConditionerOffState(Device device) {
        super(device);
        log.info(String.format("Air Conditioner in room \"%s\" was turned off %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        this.time += time;
    }
}
