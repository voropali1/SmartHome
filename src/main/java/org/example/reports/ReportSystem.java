package org.example.reports;


import org.example.SmartHome.SmartHome;
import org.example.SmartHome.Time;

import java.io.IOException;
public class ReportSystem implements Time {
    private long time = 0;
    private final ActivityAndUsageReport activityAndUsageReport = new ActivityAndUsageReport();
    private final HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport();
    private final ConsumptionReport consumptionReport = new ConsumptionReport();
    private final EventReport eventReport = new EventReport();

    public ReportSystem() {
        SmartHome.getInstance().getSimulation().follow(this);
    }
    public ActivityAndUsageReport getActivityAndUsageReport() {
        return activityAndUsageReport;
    }
   public ConsumptionReport getConsumptionReport() {
       return consumptionReport;
   }

    public EventReport getEventReport() {
        return eventReport;
    }

    private void generateConsumptionReport() throws IOException {
        consumptionReport.generateReport();
    }

    private void generateActivityAndUsageReport() throws IOException {
       activityAndUsageReport.generateReport();
    }

    private void generateHouseConfReport() throws IOException {
        houseConfigurationReport.generateReport();
    }

    @Override
    public void update(long time) throws IOException {
        this.time += time;
        if (this.time > 23 * 3600L * 1000000000L && this.time < 24 * 3600L * 1000000000L) {
            generateConsumptionReport();
            generateActivityAndUsageReport();
            this.time = 0;
        }
        if (SmartHome.getInstance().getSimulation().getDay() == 1
                && SmartHome.getInstance().getSimulation().getHour() == 0) {
            generateHouseConfReport();
        }
    }
}
