package org.example.entity.residents.person.adult.state;



import org.example.SmartHome.Simulation;
import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;

import java.util.logging.Logger;

public class AdultWorkingState extends ResidentState {
    private static final Logger log = Logger.getLogger(AdultWorkingState.class.getName());

    private final int start_home_time = 9;
    private final int end_home_time = 18;

    public AdultWorkingState(Resident resident) {
        super(resident);

        log.info(String.format("%s \"%s\" went to work %s",
                resident.getClass().getSimpleName(),
                resident.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHome.getInstance().getSimulation();

        if (simulationTime.getHour() < start_home_time || simulationTime.getHour() >= end_home_time) {
            resident.changeState(new AdultWaitingState(resident));

            log.info(String.format("%s \"%s\" arrived home from work %s",
                    resident.getClass().getSimpleName(),
                    resident.getName(),
                    SmartHome.getInstance().getSimulation().gettime()));
        }
    }
}
