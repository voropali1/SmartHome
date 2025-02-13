package org.example.entity.device.airCondicioner.state;


import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.BrokenState;
import org.example.events.IsBroken;

import java.util.logging.Logger;

public class AirConditionerBrokenState extends BrokenState {
    private static final Logger log = Logger.getLogger(AirConditionerBrokenState.class.getName());

    public AirConditionerBrokenState(Device device) {
        super(device);
        SmartHome.getInstance().getEventDispatcher().dispatchEvent(new IsBroken(device, device.getRoom()), "global");
        log.info(String.format("Air Conditioner in room \"%s\" is broken %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
