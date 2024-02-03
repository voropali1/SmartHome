package org.example.entity.device.light.state;
import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.OffState;

import java.util.logging.Logger;

public class LightOffState extends OffState {

    private static final Logger log = Logger.getLogger(LightOffState.class.getName());
    public LightOffState(Device device) {
        super(device);
        log.info(String.format("Light in room \"%s\" was turned off %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        this.time += time;
    }
}
