package org.example.entity.residents.person.adult.state;


import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;

import java.util.logging.Logger;

public class AdultOnState extends ResidentState {
    private static final Logger log = Logger.getLogger(AdultOnState.class.getName());

    public AdultOnState(Resident resident) {
        super(resident);
        log.info(String.format("%s \"%s\" started using object \"%s\" [%s]",
                resident.getClass().getSimpleName(),
                resident.getName(),
                resident.getObject().getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        this.time += time;

        if (this.time > resident.getObject().getUsageTime() * 3600L * 1000000000L) {
            resident.getObject().afterUse(resident);
            resident.changeState(new AdultWaitingState(resident));
        }
    }
}
