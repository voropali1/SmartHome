package org.example;


import org.example.SmartHome.SmartHome;

public class Main {

    public static void main(String[] args) throws Exception {
        SmartHome smartHome = SmartHome.getInstance();
        smartHome.loadDevices("src/main/java/org/example/config/house_config2.json");
        smartHome.start(20000,100000000000000000L);
    }






}

