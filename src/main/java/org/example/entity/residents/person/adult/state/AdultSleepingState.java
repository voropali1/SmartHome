package org.example.entity.residents.person.adult.state;



import org.example.SmartHome.Simulation;
import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;

import java.util.logging.Logger;

public class AdultSleepingState extends ResidentState {
    private static final Logger log = Logger.getLogger(AdultSleepingState.class.getName());

    private final int start_wake_time = 23;
    private final int end_wake_time = 7;

    public AdultSleepingState(Resident resident) {
        super(resident);
        log.info(String.format("%s \"%s\" went to bed %s",
                resident.getClass().getSimpleName(),
                resident.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHome.getInstance().getSimulation();

        if (simulationTime.getHour() < start_wake_time && simulationTime.getHour() >= end_wake_time) {
            resident.changeState(new AdultWaitingState(resident));
            log.info(String.format("%s \"%s\" woke up %s",
                    resident.getClass().getSimpleName(),
                    resident.getName(),
                    SmartHome.getInstance().getSimulation().gettime()));
        }
    }
}
