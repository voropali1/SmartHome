package org.example.entity.device.light.state;


import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.BrokenState;
import org.example.events.IsBroken;

import java.util.logging.Logger;

public class LightBrokenState extends BrokenState {
    private static final Logger log = Logger.getLogger(LightBrokenState.class.getName());
    public LightBrokenState(Device device) {
        super(device);
        SmartHome.getInstance().getEventDispatcher().dispatchEvent(new IsBroken(device, device.getRoom()), "global");
        log.info(String.format("Light in room \"%s\" is broken %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        if (device.getTime() > device.getWorkingTime() * 3600L * 1000000000L) {
            device.changeState(new LightFixingState(device));
        }
        this.time += time;
        device.setTime(device.getTime() + time);

    }
}
