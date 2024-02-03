package org.example.entity.sensor;


import org.example.place.Room;

public class LightSensor extends InternalSensor {
    private enum LightSensorState {
        ENOUGH_LIGHT,
        NOT_ENOUGH_LIGHT
    }

    private LightSensorState state = LightSensorState.ENOUGH_LIGHT;

    public LightSensor(Room room) {
        super(room);
    }

    @Override
    public void update(long time) {
        this.time =+ time;
    }

    @Override
    public void switchState() {
        state = state == LightSensorState.ENOUGH_LIGHT ? LightSensorState.NOT_ENOUGH_LIGHT : LightSensorState.ENOUGH_LIGHT;
    }
    @Override
    public void resetState() {
        state = LightSensorState.ENOUGH_LIGHT;
    }
    public LightSensorState getState() {
        return this.state;
    }
}
