package org.example.reports;



import org.example.SmartHome.SmartHome;
import org.example.entity.device.Device;
import org.example.entity.residents.person.Person;
import org.example.entity.residents.person.PersonFactory;
import org.example.entity.residents.pet.Pet;
import org.example.entity.residents.pet.PetFactory;
import org.example.place.House;
import org.example.place.HouseBuilder;
import org.example.place.Room;
import org.example.place.Floor;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating house configuration report.
 */
public class HouseConfigurationReport {
    private FileWriter houseConfReport = null;
    private HouseBuilder houseBuilder;

    public HouseConfigurationReport() {
        //this.houseConfReport = houseBuilder;
    }

    /**
     * Generates report.
     * @throws IOException writing to file is unsuccessful
     */
    public void generateReport() throws IOException {
        try {
            this.houseConfReport = new FileWriter("HouseConfigurationReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        House house = SmartHome.getInstance().getHouse();
        List<Person> persons = new ArrayList<>(PersonFactory.getInstance().getPersons());
        List<Pet> pets = new ArrayList<>(PetFactory.getInstance().getPets());
        int residents = persons.size() + pets.size();

        houseConfReport.write("House\n");
        houseConfReport.write("House has " + house.getFloors().size() + " floor(s).\n");

        for (Floor floor : house.getFloors()) {
            houseConfReport.write("Floor " + "number " + floor.getLevel() + " has "
                + floor.getRooms().size() + " rooms: \n");
            for (Room room : floor.getRooms()) {
                houseConfReport.write(String.format("Room \"%s\" has %s device(s): \n",
                        room.getName(),
                        room.getDevices().size()));
                for (Device device : room.getDevices()) {
                    houseConfReport.write(device.getName() + "\n");
                }
            }
        }
        houseConfReport.write("Residents\n");
        houseConfReport.write("There are " + residents + " inhabitants living in the house: "
                + persons.size() + " people and " + pets.size() + " pet(s).\n");

        houseConfReport.write("People: \n");
        for (Person person : persons) {
            houseConfReport.write(String.format("%s \"%s\" \n",
                    person.getClass().getSimpleName(),
                    person.getName()));
        }

        houseConfReport.write("Pets: \n");
        for (Pet pet : pets) {
            houseConfReport.write(String.format("%s \"%s\" \n",
                    pet.getClass().getSimpleName(),
                    pet.getName()));
        }

        houseConfReport.flush();
        houseConfReport.close();
    }

}
