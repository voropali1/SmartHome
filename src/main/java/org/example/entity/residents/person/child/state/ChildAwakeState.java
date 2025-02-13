package org.example.entity.residents.person.child.state;



import org.example.SmartHome.Simulation;
import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;
import org.example.entity.device.Device;
import org.example.entity.device.state.IdleState;
import org.example.entity.residents.person.child.Child;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChildAwakeState extends ResidentState {
    private static final Logger log = Logger.getLogger(ChildAwakeState.class.getName());
    private final double min_trigger_time = 2;
    private final double max_trigger_time = 24;
    private final int start_sleeping_time = 21;
    private final int end_sleeping_time =  8;
    private long timeFromLastDispatchedDeviceUsingEvent = 0;
    private double triggeredTime;

    public ChildAwakeState(Resident resident) {
        super(resident);
        log.info(String.format("Child \"%s\" woke up %s",
                resident.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
        this.triggeredTime = calculateTriggeredTime();
    }

    private double calculateTriggeredTime() {
        return Math.random() * (max_trigger_time - min_trigger_time + 1) + min_trigger_time;
    }

    private void useDeviceAndResetDeviceUsingEvent(Device device) {
        this.timeFromLastDispatchedDeviceUsingEvent = 0;
        this.triggeredTime = calculateTriggeredTime();
//
        if (Math.random() <= resident.getDeviceBreakingChance()) {
            log.info(String.format("%s \"%s\" broke the object \"%s\" while playing with it %s",
                    resident.getClass().getSimpleName(),
                    resident.getName(),
                    device.getName(),
                    SmartHome.getInstance().getSimulation().gettime()));
//
            device.toBreak();
        } else {
            log.info(String.format("%s \"%s\" didn't break the object \"%s\" while playing with it %s",
                    resident.getClass().getSimpleName(),
                    resident.getName(),
                    device.getName(),
                    SmartHome.getInstance().getSimulation().gettime()));
        }
    }
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedDeviceUsingEvent += time;

        Simulation simulationTime = SmartHome.getInstance().getSimulation();

        if (!((Child) resident).getDispatchedHungerEvent() && !((Child) resident).getDispatchedPoopedEvent()) {
            if (simulationTime.getHour() >= start_sleeping_time || simulationTime.getHour() < end_sleeping_time) {
                resident.changeState(new ChildSleepingState(resident));
                return;
            }
        }
        if (this.timeFromLastDispatchedDeviceUsingEvent >= triggeredTime * 3600L * 1000000000L) {
            List<Device> devices = resident.getRoom().getDevices().stream()
                    .filter(device -> device.getState() instanceof IdleState)
                    .collect(Collectors.toList());
            if (devices.isEmpty()) {
                log.info(String.format("Child \"%s\" didn't find any available device in room \"%s\"%s",
                        resident.getName(),
                        resident.getRoom().getName(),
                        SmartHome.getInstance().getSimulation().gettime()));
            } else {
                Device device = devices.get((int) (Math.random() * devices.size()));
                useDeviceAndResetDeviceUsingEvent(device);
            }
        }
    }
}
