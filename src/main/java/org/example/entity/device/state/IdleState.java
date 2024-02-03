package org.example.entity.device.state;

import org.example.entity.device.Device;
import org.example.entity.device.DeviceState;

public abstract class IdleState extends DeviceState {
    /**
     * Instantiates a new device idle state.
     *
     * @param device the device
     */
    public IdleState(Device device) {
        super(device);
    }
}