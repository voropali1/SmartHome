package org.example.entity.residents;

public abstract class ResidentState {
    protected Resident resident;

    // State
    protected long time = 0;

    public ResidentState(Resident resident) {
        this.resident = resident;
    }

    /**
     * Update
     * @param time the time
     */
    public abstract void update(long time);
}
