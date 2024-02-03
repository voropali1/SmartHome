package org.example.reports;



import org.example.SmartHome.SmartHome;
import org.example.entity.device.Device;
import org.example.entity.device.DeviceFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating consumption report.
 */
public class ConsumptionReport {
    private FileWriter consumptionReport = null;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Instantiates a new Consumption report.
     */
    public ConsumptionReport() {
        try {
            this.consumptionReport = new FileWriter("ConsumptionReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates report.
     *
     * @throws IOException writing to file is unsuccessful
     */
    public void generateReport() throws IOException {
        List<Device> allDevices = new ArrayList<>(DeviceFactory.getInstance().getDevices());
        double totalElectricity = 0;
        double totalWater = 0;
        double totalGas = 0;
        int day = SmartHome.getInstance().getSimulation().getDay();
        consumptionReport.write(day + "day\n");
        // Electricity consumption
        consumptionReport.write("Electricity consumption\n");
        for (Device device : allDevices) {
            double elUsed = device.calculateElectricity();
            totalElectricity += elUsed;
            consumptionReport.write(device.getName() + " has used " + df.format(elUsed) + " KwH " + "electricity for today.\n");
        }
        consumptionReport.write("Total electricity used by day: " + df.format(totalElectricity) + " KwH.\n");
        // Water consumption
        consumptionReport.write("Water consumption\n");
        for (Device device : allDevices) {
            double waterUsed = device.calculateWater();
            totalWater += waterUsed;
            consumptionReport.write(device.getName() + " has used " + df.format(waterUsed) + " litre " + "water for today.\n");
        }
        consumptionReport.write("Total water used by day: " + df.format(totalWater) + " litre.\n");
        // Gas consumption
        consumptionReport.write("Gas consumption\n");
        for (Device device : allDevices) {
            double gasUsed = device.calculateGas();
            totalGas += gasUsed;
            consumptionReport.write(device.getName() + " has used " + df.format(gasUsed) + " cu. m. " + "gas for today.\n");
        }
        consumptionReport.write("Total gas used by day: " + df.format(totalGas) + " cu. m.\n");

        consumptionReport.flush();
    }
}
