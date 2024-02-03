package org.example.entity.residents.pet.state;



import org.example.SmartHome.Simulation;
import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;
import org.example.entity.device.Device;
import org.example.entity.device.DeviceFactory;
import org.example.entity.device.state.IdleState;
import org.example.entity.residents.person.child.state.ChildAwakeState;
import org.example.entity.residents.pet.Pet;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PetAwakeState extends ResidentState {
    private static final Logger log = Logger.getLogger(ChildAwakeState.class.getName());
    private final double min_trigger_time = 6;
    private final double max_trigger_time = 24;
    private final int start_sleeping_time = 22;
    private final int end_sleeping_time = 8;

    private long timeFromLastDispatchedDeviceUsingEvent = 0;
    private double triggeredTime;

    public PetAwakeState(Resident resident) {
        super(resident);
        this.triggeredTime = calculateTriggeredTime();
    }

    private double calculateTriggeredTime() {
        return Math.random() * (max_trigger_time - min_trigger_time + 1) + min_trigger_time;
    }

    private void useDeviceAndResetDeviceUsingEvent(Device device) {
        this.timeFromLastDispatchedDeviceUsingEvent = 0;
        this.triggeredTime = calculateTriggeredTime();
        if (Math.random() <= resident.getDeviceBreakingChance()) {
            log.info(String.format("%s \"%s\" broke the object \"%s\" while playing with it %s",
                    resident.getClass().getSimpleName(),
                    resident.getName(),
                    device.getName(),
                    SmartHome.getInstance().getSimulation().gettime()));
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

        if (!((Pet) resident).getDispatchedHungerEvent()) {
            if (simulationTime.getHour() >= start_sleeping_time || simulationTime.getHour() < end_sleeping_time) {
                resident.changeState(new PetSleepingState(resident));
                return;
            }
        }
        if (this.timeFromLastDispatchedDeviceUsingEvent >= triggeredTime * 3600L * 1000000000L) {
            List<Device> devices = resident.getRoom().getDevices().stream()
                    .filter(device -> device.getState() instanceof IdleState)
                    .collect(Collectors.toList());
            if (devices.isEmpty()) {
                List<Device> allDevices = DeviceFactory.getInstance().getDevices().stream()
                        .filter(device -> device.getState() instanceof IdleState)
                        .collect(Collectors.toList());
                if (!allDevices.isEmpty()) {
                    Device device = allDevices.get((int) (Math.random() * allDevices.size()));
                    resident.moveTo(device.getRoom());
                    useDeviceAndResetDeviceUsingEvent(device);
                }
            } else {
                Device device = devices.get((int) (Math.random() * devices.size()));
                useDeviceAndResetDeviceUsingEvent(device);
            }
        }
    }
}
