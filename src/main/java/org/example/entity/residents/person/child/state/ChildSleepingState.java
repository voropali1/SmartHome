package org.example.entity.residents.person.child.state;



import org.example.SmartHome.Simulation;
import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;

import java.util.logging.Logger;
public class ChildSleepingState extends ResidentState {
    private static final Logger log = Logger.getLogger(ChildSleepingState.class.getName());
    private final int start_wake_time = 19;
    private final int end_wake_time = 6;
    public ChildSleepingState(Resident resident) {
        super(resident);
        log.info(String.format("Child \"%s\" went to bed %s",
                resident.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHome.getInstance().getSimulation();

        if (simulationTime.getHour() < start_wake_time && simulationTime.getHour() >= end_wake_time) {
            resident.changeState(new ChildAwakeState(resident));
            log.info(String.format("Child \"%s\" woke up %s",
                    resident.getName(),
                    SmartHome.getInstance().getSimulation().gettime()));
        }
    }
}
