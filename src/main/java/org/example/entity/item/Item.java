package org.example.entity.item;

import org.example.SmartHome.SmartHome;
import org.example.SmartHome.Time;
import org.example.entity.residents.Resident;
import org.example.entity.Object;
import org.example.entity.residents.person.adult.Adult;
import org.example.events.Source;
import org.example.place.Room;
public abstract class Item implements Object, Time, Source {
    protected int workingTime = 0;
    protected double usageTime = 0;
    protected double fixingTime = 0;
    protected int leisure = 0;
    protected long time = 0;
    private final String name;
    private final Room room;
    protected Resident user = null;
    protected ItemState state;
    private boolean isUsing = false;
    private boolean isBroken = false;

    public Item(Room room, String name) {
        this.room = room;
        this.name = name;
        SmartHome.getInstance().getSimulation().follow(this);
    }

    public void changeState(ItemState state) {
        this.state = state;
    }
    public Room getRoom() {
        return room;
    }
    public String getName() {
        return name;
    }
    public double getUsageTime() {
        return usageTime;
    }
   public void use(Resident resident) {
       ((Adult) resident).useObject(this);
       isUsing = true;
   }
     public void afterUse(Resident resident) {
         ((Adult) resident).afterUseObject(this);
         isUsing = false;
     }
    public Resident getUser() {
        return user;
    }

    public void setUser(Resident resident) {
        user = resident;
    }

    public boolean isUsing() {
        return isUsing;
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
    public boolean isBroken() {
        return isBroken;
    }
}