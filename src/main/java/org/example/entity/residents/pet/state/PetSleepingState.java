package org.example.entity.residents.pet.state;


import org.example.SmartHome.Simulation;
import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;

import java.util.logging.Logger;

public class PetSleepingState extends ResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(PetSleepingState.class.getName());

    //Constants
    private final int start_wake_time = 22;

    private final int end_wake_time = 6;

    public PetSleepingState(Resident resident) {
        super(resident);
        log.info(String.format("Pet \"%s\" is sleeping [%s]",
                resident.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHome.getInstance().getSimulation();

        if (simulationTime.getHour() < start_wake_time && simulationTime.getHour() >= end_wake_time) {
            resident.changeState(new PetAwakeState(resident));
            log.info(String.format("Pet \"%s\" woke up %s",
                    resident.getName(),
                    SmartHome.getInstance().getSimulation().gettime()));
        }

    }
}