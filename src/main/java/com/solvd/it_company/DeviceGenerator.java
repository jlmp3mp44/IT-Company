package com.solvd.it_company;

import java.util.ArrayList;
import java.util.List;

public class DeviceGenerator {

    private static int lapTopIndex = 0;
    private static int mouseIndex = 0;
    private static int screenSizeIndex = 0;
    private static int memorySizesIndex = 0;
    private static int wirelessMouseIndex = 0;
    private static int hasSensorMouseIndex = 0;
    protected static List<String> namesLapTops = new ArrayList<>();
    protected static List<String> namesMouses = new ArrayList<>();
    protected static List<Double> screenSizes = new ArrayList<>();
    protected static List<Integer> memorySize = new ArrayList<>();
    protected static List<Boolean> wireless = new ArrayList<>();
    protected static List<Boolean> hasSensor = new ArrayList<>();

    static {
        namesLapTops.add("Lenovo");
        namesLapTops.add("Apple");
        namesLapTops.add("Samsung");
        namesLapTops.add("Lenovo");
        namesLapTops.add("Lenovo");
        namesLapTops.add("Lenovo");
        namesLapTops.add("Apple");
        namesLapTops.add("Apple");
        namesLapTops.add("Apple");
        namesLapTops.add("Lenovo");
        namesLapTops.add("Apple");
        namesLapTops.add("Samsung");

        screenSizes.add(14.0);
        screenSizes.add(15.6);
        screenSizes.add(13.0);
        screenSizes.add(14.0);
        screenSizes.add(15.0);
        screenSizes.add(13.5);
        screenSizes.add(14.8);
        screenSizes.add(15.5);
        screenSizes.add(13.3);
        screenSizes.add(14.0);
        screenSizes.add(15.5);
        screenSizes.add(17.2);

        memorySize.add(16);
        memorySize.add(32);
        memorySize.add(8);
        memorySize.add(16);
        memorySize.add(16);
        memorySize.add(16);
        memorySize.add(32);
        memorySize.add(8);
        memorySize.add(8);
        memorySize.add(8);
        memorySize.add(32);
        memorySize.add(32);


        namesMouses.add("Lenovo");
        namesMouses.add("Apple");
        namesMouses.add("Samsung");
        namesMouses.add("Lenovo");
        namesMouses.add("Lenovo");
        namesMouses.add("Lenovo");
        namesMouses.add("Apple");
        namesMouses.add("Apple");
        namesMouses.add("Apple");
        namesMouses.add("Lenovo");
        namesMouses.add("Apple");
        namesMouses.add("Samsung");

        wireless.add(true);
        wireless.add(false);
        wireless.add(true);
        wireless.add(true);
        wireless.add(true);
        wireless.add(true);
        wireless.add(true);
        wireless.add(true);
        wireless.add(false);
        wireless.add(false);
        wireless.add(false);
        wireless.add(true);

        hasSensor.add(true);
        hasSensor.add(true);
        hasSensor.add(false);
        hasSensor.add(true);
        hasSensor.add(true);
        hasSensor.add(false);
        hasSensor.add(true);
        hasSensor.add(true);
        hasSensor.add(false);
        hasSensor.add(true);
        hasSensor.add(true);
        hasSensor.add(true);


    }

    //methods to iterate the properties of  devices
    public static String getNextDeviceName(String device) {
        switch (device) {
            case "LapTop":
                String nextLapTopName = namesLapTops.get(lapTopIndex);
                lapTopIndex++;
                return nextLapTopName;
            case "Mouse":
                String nextMouseName = namesMouses.get(mouseIndex);
                mouseIndex++;
                return nextMouseName;
            default:
                return "No such device";
        }
    }


    public static double getNextLapTopScreenSize() {
        double nextLapTopSreenSize = screenSizes.get(screenSizeIndex);
        screenSizeIndex++;
        return nextLapTopSreenSize;
    }

    public static int getNextLapTopMemorySize() {
        int nextLapTopMemorySize = memorySize.get(memorySizesIndex);
        memorySizesIndex++;
        return nextLapTopMemorySize;
    }

    public static boolean getNextMouseWireless() {
        boolean nextMouseWireless = wireless.get(wirelessMouseIndex);
        wirelessMouseIndex++;
        return nextMouseWireless;
    }

    public static boolean getNextMouseSensor() {
        boolean nextMouseSensor = hasSensor.get(hasSensorMouseIndex);
        hasSensorMouseIndex++;
        return nextMouseSensor;
    }
}

