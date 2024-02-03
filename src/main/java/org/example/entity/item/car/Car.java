package org.example.entity.item.car;


import org.example.entity.residents.Resident;
import org.example.entity.item.Item;
import org.example.entity.item.car.state.CarBrokenState;
import org.example.entity.item.car.state.CarIdleState;
import org.example.entity.item.car.state.CarOnState;
//import org.example.entity.residents.person.person.Adult;
import org.example.entity.residents.person.adult.Adult;
import org.example.place.Room;

public class Car extends Item {
    public Car(Room room, String name) {
        super(room, name);
        this.state = new CarIdleState(this);
        this.workingTime = 2000;
        this.usageTime = 2;
        this.leisure = 40;
    }
    @Override
    public void use(Resident resident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= resident.getDeviceBreakingChance()) {
                changeState(new CarBrokenState(this));
                return;
            }
            if (!(resident instanceof Adult)) return;
            setUser(resident);
            ((Adult) resident).useObject(this);
            changeState(new CarOnState(this));
        }
    }
    @Override
    public void afterUse(Resident resident) {
        if (isUsing() && resident.equals(getUser())) {
            setUser(null);
            ((Adult) resident).afterUseObject(this);
            changeState(new CarIdleState(this));
        }
    }
@Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
