package org.example.entity.device.state;

import org.example.entity.device.Device;
import org.example.entity.device.DeviceState;

public abstract class OffState  extends DeviceState {
        /**
         * Instantiates a new device off state.
         *
         * @param device the device
         */
        public OffState(Device device) {
            super(device);
        }
    }
