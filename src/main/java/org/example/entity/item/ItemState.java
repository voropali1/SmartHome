package org.example.entity.item;


public abstract class ItemState {
    protected long time;
    protected Item item;
    public ItemState(Item item) {
        this.item = item;
    }
    public abstract void update(long time);
}
