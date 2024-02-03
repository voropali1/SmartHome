package org.example.entity.sensor;

import org.example.place.Room;

public class TemperatureSensor extends InternalSensor {
    private enum TemperatureSensorState {
    ENOUGH_TEMP,
    NOT_ENOUGH_TEMP
}

private TemperatureSensorState state = TemperatureSensor.TemperatureSensorState.ENOUGH_TEMP;
    public TemperatureSensor(Room room) {
        super(room);
    }

    @Override
    public void switchState() {
        state = state == TemperatureSensorState.ENOUGH_TEMP ? TemperatureSensorState.NOT_ENOUGH_TEMP : TemperatureSensorState.ENOUGH_TEMP;
    }
    @Override
    public void resetState() {
        state = TemperatureSensorState.ENOUGH_TEMP;
    }
    @Override
    public void update(long time) {
        this.time =+ time;
    }

}
