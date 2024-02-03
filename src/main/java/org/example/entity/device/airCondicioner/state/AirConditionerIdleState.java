package org.example.entity.device.airCondicioner.state;

import org.example.entity.device.Device;
import org.example.SmartHome.SmartHome;
import org.example.entity.device.state.IdleState;

import java.util.logging.Logger;

public class AirConditionerIdleState extends IdleState {
    private static final Logger log = Logger.getLogger(AirConditionerIdleState.class.getName());

    public AirConditionerIdleState(Device device) {
        super(device);
        this.electricityConsumption = 0.1;
        log.info(String.format("Air Conditioner in \"%s\" is not working %s",
                device.getRoom().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        if (device.getTime() > device.getWorkingTime() * 3600L * 1000000000L) {
            device.changeState(new AirConditionerOffState(device));
        }
        this.time += time;
        device.setTime(device.getTime() + time);
        device.setCurrentElectricity(device.getCurrentElectricity()
                + (electricityConsumption / (3600D * 1000000000)) * time);
    }
    }

