package org.example.entity.item;



import org.example.entity.item.car.Car;
import org.example.place.Room;

import java.util.ArrayList;
import java.util.List;
public class ItemFactory {
    private static ItemFactory instance;
    private final List<Item> items = new ArrayList<>();

    private ItemFactory() {}
    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }

        return instance;
    }

    public Item create(String type, Room room, String name) throws IllegalArgumentException {
        Item item = switch (type) {
            case "CAR" -> new Car(room, name);
            default -> throw new IllegalArgumentException("Type of item " + type + " was not found.");
        };
        this.items.add(item);
        return item;
    }
    public List<Item> getItems() {
        return this.items;
    }
}
