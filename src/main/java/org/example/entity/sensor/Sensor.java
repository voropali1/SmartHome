package org.example.entity.sensor;


import org.example.SmartHome.SmartHome;
import org.example.SmartHome.Time;
import org.example.events.Source;
public abstract class Sensor implements Time, Source {
    protected long time = 0;

    public Sensor() {
        SmartHome.getInstance().getSimulation().follow(this);
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }

}
