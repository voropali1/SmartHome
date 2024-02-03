package org.example.entity.residents.person.adult.state;


import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;
import org.example.entity.device.Device;
import org.example.entity.item.Item;

import java.util.logging.Logger;

public class AdultFixingState extends ResidentState {
    private static final Logger log = Logger.getLogger(AdultFixingState.class.getName());

    public AdultFixingState(Resident resident) {
        super(resident);
        log.info(String.format("%s \"%s\" started fixing object \"%s\" %s",
                resident.getClass().getSimpleName(),
                resident.getName(),
                resident.getObject().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
        if(resident.getObject() instanceof Device){
        if (this.time > ((Device) resident.getObject()).getFixingTime() * 3600L * 1000000000L) {
            ((Device) resident.getObject()).isOn();

            resident.changeState(new AdultWaitingState(resident));
        }}
        else {
            if (this.time > ((Item) resident.getObject()).getFixingTime() * 3600L * 1000000000L) {
                resident.changeState(new AdultWaitingState(resident));
            }
        }
    }
}
