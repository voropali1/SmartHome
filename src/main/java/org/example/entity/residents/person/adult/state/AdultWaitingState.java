package org.example.entity.residents.person.adult.state;

import org.example.SmartHome.Simulation;
import org.example.SmartHome.SmartHome;
import org.example.entity.residents.Resident;
import org.example.entity.residents.ResidentState;
import org.example.entity.device.Device;
import org.example.entity.device.DeviceFactory;
import org.example.entity.device.state.IdleState;
import org.example.entity.item.Item;
import org.example.entity.item.ItemFactory;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AdultWaitingState extends ResidentState {
    private final double waitingTime = 0.4;
    private final int start_working_time = 8;
    private final int end_working_time = 16;
    private final int star_sleeping_time = 22;
    private final int end_sleeping_time = 6;
    private static final Logger log = Logger.getLogger(AdultWorkingState.class.getName());

    public AdultWaitingState(Resident resident) {
        super(resident);
        log.info(String.format("%s \"%s\" in waiting\" %s",
                resident.getClass().getSimpleName(),
                resident.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHome.getInstance().getSimulation();

        if (simulationTime.getHour() >= star_sleeping_time || simulationTime.getHour() < end_sleeping_time) {
            resident.changeState(new AdultSleepingState(resident));
            return;
        }

        if (simulationTime.getHour() >= start_working_time && simulationTime.getHour() < end_working_time)
        {
            resident.changeState(new AdultWorkingState(resident));
            return;
        }
        if (this.time < waitingTime * 3600L * 1000000000L) return;

        if (Math.random() < 0.5) {
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
                    device.use(resident);
                }
            } else {
                devices.get((int) (Math.random() * devices.size())).use(resident);
            }
        } else {
            List<Item> items = resident.getRoom().getItems().stream()
                    .filter(item -> !item.isUsing())
                    .collect(Collectors.toList());
            if (items.isEmpty()) {
                List<Item> allItems = ItemFactory.getInstance().getItems().stream()
                        .filter(item -> !item.isUsing())
                        .collect(Collectors.toList());
                if (!allItems.isEmpty()) {
                    Item item = allItems.get((int) (Math.random() * allItems.size()));
                    resident.moveTo(item.getRoom());
                    item.use(resident);
                }
            } else {
                items.get((int) (Math.random() * items.size())).use(resident);
            }
        }
    }
}
