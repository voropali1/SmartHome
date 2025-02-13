package org.example.entity.device.airCondicioner;

import org.example.entity.device.Device;
import org.example.entity.residents.Resident;
import org.example.entity.device.Info;
import org.example.entity.device.airCondicioner.state.*;
import org.example.entity.residents.person.adult.Adult;
import org.example.place.Room;

public class airConditioner extends Device {
    public airConditioner(Room room, String name) {
        super(room, name);
        this.state = new AirConditionerIdleState(this);
        this.fixingTime = 12;
        this.workingTime = 200;
        this.usageTime = 0;
        this.info = new Info(this, this.fixingTime);
    }

    @Override
    public void use(Resident resident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= resident.getDeviceBreakingChance()) {
                changeState(new AirConditionerBrokenState(this));
                return;
            }
            if (!(resident instanceof Adult)) return;
            setUser(resident);
            ((Adult) resident).useObject(this);
            changeState(new AirConditionerOnState(this));
        }
    }

    @Override
    public void afterUse(Resident resident) {
        if (isUsing() && resident.equals(getUser())) {
            setUser(null);
            ((Adult) resident).afterUseObject(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new AirConditionerFixingState(this));
        }
    }

    @Override
    public void toBreak() {
        changeState(new AirConditionerBrokenState(this));
    }

    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}

