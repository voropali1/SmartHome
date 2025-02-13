package org.example.entity.device.light.state;



import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.FixingState;

import java.util.logging.Logger;

public class LightFixingState extends FixingState {
    private static final Logger log = Logger.getLogger(LightFixingState.class.getName());
    public LightFixingState(Device device) {
        super(device);
        log.info(String.format("Light in room \"%s\" is being fixed %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        this.time += time;
        if (device.getTime() > device.getWorkingTime() * 3600L * 1000000000L) {
            device.changeState(new LightIdleState(device));
        }
        device.setTime(device.getTime() + time);
    }
}
