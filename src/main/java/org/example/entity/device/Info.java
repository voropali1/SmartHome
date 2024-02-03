package org.example.entity.device;

import org.example.SmartHome.SmartHome;

import java.util.logging.Logger;

public class Info {
    private static final Logger log = Logger.getLogger(Info.class.getName());
    private final Device device;
    private final double fixingTime;

    /**
     * Instantiates a new Documentation.
     *
     * @param device            the device
     * @param fixingTime the fixing time in hours
     */
    public Info(Device device, double fixingTime) {
        this.device = device;
        this.fixingTime = fixingTime;
    }
    public void fixDevice(Device device) {
        log.info(String.format("Reading documentation for the device \"%s\" %s",
                device.getName(),
                SmartHome.getInstance().getSimulation().gettime()));
    }
}