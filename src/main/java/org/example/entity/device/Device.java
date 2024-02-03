package org.example.entity.device;

import org.example.SmartHome.SmartHome;
import org.example.SmartHome.Time;
import org.example.entity.residents.Resident;
import org.example.entity.Object;
import org.example.entity.device.state.*;
import org.example.entity.residents.person.adult.Adult;
import org.example.events.Source;
import org.example.place.Room;

public abstract class Device implements Object, Time, Source {

    protected Room room;
    protected DeviceState state;
    protected String name;
    protected Resident user = null;
    protected Adult adult = null;
    protected long time = 0;

    protected int workingTime = 0;
    protected double usageTime = 0;
    protected Info info;

    protected double cur_electricity = 0;
    protected double cur_water = 0;
    protected double cur_gas = 0;
    protected double lastElectricity = 0;
    protected double lastWater = 0;
    protected double lastGas = 0;
    protected double fixingTime = 0;
    public Device(Room room, String name) {
        this.room = room;
        this.name = name;
        SmartHome.getInstance().getSimulation().follow(this);
    }

    public double getCurrentElectricity() {
        return cur_electricity;
    }
    public void setCurrentElectricity(double consumption) {
        cur_electricity = consumption;
    }
    public double getCurrentWater() {
        return cur_electricity;
    }
    public void setCurrentWater(double consumption) {
        cur_electricity = consumption;
    }
    public double calculateElectricity() {
        double result = cur_electricity - lastElectricity;
        lastElectricity = cur_electricity;

        return result;
    }
    public double calculateWater() {
        double result = cur_water - lastWater;
        lastWater = cur_water;

        return result;
    }
    public double calculateGas() {
        double result = cur_gas - lastGas;
        lastGas = cur_gas;
        return result;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getWorkingTime() {
        return workingTime;
    }
    public double getFixingTime() {
        return fixingTime;
    }
    public DeviceState getState() {
        return state;
    }
    public Resident getUser() {
        return user;
    }
    public void setUser(Resident resident) {
        user = resident;
    }

    public boolean isOff() {
        return state instanceof OffState;
    }

    public boolean isOn() {
        return state instanceof IdleState;
    }

    public boolean isBroken() {
        return state instanceof BrokenState;
    }

    public boolean isFixing() {
        return state instanceof FixingState;
    }
    @Override
    public String getName() {
        return name;
    }
   @Override
   public double getUsageTime() {
       return usageTime;
   }
    @Override
    public boolean isUsing() {
       return state instanceof OnState;
   }

    public void changeState(DeviceState state) {
        this.state = state;
    }
    public Room getRoom() {
        return room;
    }
    public Info getInfo() {
        return info;
    }
    //public abstract void off();
    public abstract void fix(Adult adult);
    public abstract void toBreak();
}
