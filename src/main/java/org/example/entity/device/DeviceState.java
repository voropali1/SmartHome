package org.example.entity.device;

public abstract class DeviceState {
    protected long time;
    protected Device device;
    protected double electricityConsumption = 0;
    protected double waterConsumption = 0;
    protected double gasConsumption = 0;

    /**
     * Instantiates a new device state.
     *
     * @param device the device
     */
    public DeviceState(Device device) {
        this.device = device;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public double getGasConsumption() {
        return gasConsumption;
    }

    public abstract void update(long time);
}
