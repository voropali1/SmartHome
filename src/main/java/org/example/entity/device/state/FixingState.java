package org.example.entity.device.state;

import org.example.entity.device.Device;
import org.example.entity.device.DeviceState;

public abstract class FixingState extends DeviceState {
    /**
     * Instantiates a new device fixing state.
     *
     * @param device the device
     */
    public FixingState(Device device) {
        super(device);
    }
}