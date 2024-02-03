package org.example.entity.device.state;

import org.example.entity.device.Device;
import org.example.entity.device.DeviceState;

public abstract class BrokenState extends DeviceState {
    /**
     * Instantiates a new device broken state.
     *
     * @param device the device
     */
    public BrokenState(Device device) {
        super(device);
    }
}