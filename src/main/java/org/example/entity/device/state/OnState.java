package org.example.entity.device.state;

import org.example.entity.device.Device;
import org.example.entity.device.DeviceState;

/**
 * Abstract class representing device's using state.
 */
public abstract class OnState extends DeviceState {
    /**
     * Instantiates a new device using state.
     *
     * @param device the device
     */
    public OnState(Device device) {
        super(device);
    }
}
