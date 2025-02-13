package org.example.entity.device.airCondicioner.state;

import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.OnState;

import java.util.logging.Logger;
public class AirConditionerOnState extends OnState {
    private static final Logger log = Logger.getLogger(AirConditionerOnState.class.getName());

    public AirConditionerOnState(Device device) {
        super(device);
        this.electricityConsumption = 0.4;
        log.info(String.format("Air Conditioner in room \"%s\" is working %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        if (device.getTime() > device.getWorkingTime() * 3600L * 1000000000L) {
            device.changeState(new AirConditionerBrokenState(device));
        }
        this.time += time;
        device.setTime(device.getTime() + time);
        device.setCurrentElectricity(device.getCurrentElectricity()
                + (electricityConsumption / (3600D * 1000000000)) * time);
    }
}
