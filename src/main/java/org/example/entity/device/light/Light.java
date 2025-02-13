package org.example.entity.device.light;

import org.example.entity.device.Device;
import org.example.entity.residents.Resident;
import org.example.entity.device.Info;
import org.example.entity.device.light.state.*;
import org.example.entity.residents.person.adult.Adult;
import org.example.place.Room;

public class Light extends Device {

    public Light(Room room, String name) {
        super(room, name);
        this.state = new LightIdleState(this);
        this.fixingTime = 0.5;
        this.workingTime = 500;
        this.usageTime = 0;
        this.info = new Info(this, this.fixingTime);
    }

    @Override
    public void use(Resident resident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= resident.getDeviceBreakingChance()) {
                changeState(new LightBrokenState(this));
                return;
            }
            if (!(resident instanceof Adult)) return;
            setUser(resident);
            ((Adult) resident).useObject(this);
            changeState(new LightOnState(this));
        }
    }

    @Override
    public void afterUse(Resident resident) {
        if (isUsing() && resident.equals(getUser())) {
            setUser(null);
            ((Adult) resident).afterUseObject(this);
            changeState(new LightIdleState(this));
        }
    }
    @Override
    public void fix(Adult adult) {
        if (isBroken()) {
            setUser(adult);
            adult.fixDevice(this);
            changeState(new LightFixingState(this));
        }
    }
    @Override
    public void toBreak() {
        changeState(new LightBrokenState(this));
    }

    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}

