package org.example.entity.device.airCondicioner.state;



import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.FixingState;

import java.util.logging.Logger;

public class AirConditionerFixingState extends FixingState {
    private static final Logger log = Logger.getLogger(AirConditionerFixingState.class.getName());

    public AirConditionerFixingState(Device device) {
        super(device);
        log.info(String.format("Air Conditioner in room \"%s\" is being fixed %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
