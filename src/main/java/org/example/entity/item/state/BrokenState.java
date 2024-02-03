package org.example.entity.item.state;

import org.example.entity.device.Device;
import org.example.entity.device.DeviceState;
import org.example.entity.item.Item;
import org.example.entity.item.ItemState;

public abstract class BrokenState extends ItemState {

    public BrokenState(Item item) {
        super(item);
    }
}