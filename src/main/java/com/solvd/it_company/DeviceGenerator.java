package com.solvd.it_company;

public class DeviceGenerator {
    public enum LapTopEnum{
        LENOVO1("Lenovo", 14.6, 32),
        LENOVO2("Lenovo", 15.0, 64),
        APPLE1("Apple", 13.8, 64),
        APPLE2("Apple", 12.5, 64),
        ASUS1("Asus", 15.9, 32),
        ASUS2("Asus", 16.2, 32),
        LENOVO3("lenovo", 14.8, 8),
        LENOVO4("Lenovo", 13.6, 16),
        APPLE3("Apple", 14.5, 32),
        APPLE4("Apple", 12.8, 64),
        ASUS3("Asus", 13.0, 32),
        ASUS4("Asus", 14.6, 64);
        private String name;
        private Double screenSize;
        private Integer memorySize;

        LapTopEnum(String name, Double screenSize, Integer memorySize) {
            this.name = name;
            this.screenSize = screenSize;
            this.memorySize = memorySize;
        }

        public String getName() {
            return name;
        }

        public Double getScreenSize() {
            return screenSize;
        }

        public Integer getMemorySize() {
            return memorySize;
        }
    }
    public enum MouseEnum{
        LENOVO1("Lenovo", true, true),
        LENOVO2("Lenovo", true, true),
        APPLE1("Apple", true, false),
        APPLE2("Apple", false, false),
        ASUS1("Asus", false, true),
        ASUS2("Asus", false, true),
        LENOVO3("lenovo", true, false),
        LENOVO4("Lenovo", true, true),
        APPLE3("Apple", true, false),
        APPLE4("Apple", false, true),
        ASUS3("Asus", false, false),
        ASUS4("Asus", true, true);
        private String name;
        private Boolean wireless;
        private Boolean hasSensor;

        MouseEnum(String name, Boolean wireless, Boolean hasSensor) {
            this.name = name;
            this.wireless = wireless;
            this.hasSensor = hasSensor;
        }

        public String getName() {
            return name;
        }

        public Boolean getWireless() {
            return wireless;
        }

        public Boolean getHasSensor() {
            return hasSensor;
        }
    }

    private static int lapTopIndex = 0;
    private static int mouseIndex = 0;

    //methods to iterate the properties of  devices
    public static String getNextDeviceName(String device) {
        switch (device) {
            case "LapTop":
                LapTopEnum[] lapTopNames =  LapTopEnum.values();
                LapTopEnum nextLapTopName =  lapTopNames[lapTopIndex];
                lapTopIndex++;
                return  nextLapTopName.toString();

            case "Mouse":
                MouseEnum[] mouseNames =  MouseEnum.values();
                MouseEnum nextMouseName =  mouseNames[mouseIndex];
                mouseIndex++;
                return  nextMouseName.toString();
            default:
                return "No such device";
        }
    }


    public static double getNextLapTopScreenSize(LapTopEnum lapTopEnum) {
        return lapTopEnum.getScreenSize();
    }

    public static Integer getNextLapTopMemorySize(LapTopEnum lapTopEnum) {
        return lapTopEnum.getMemorySize();
    }

    public static boolean getNextMouseWireless(MouseEnum mouseEnum) {
       return  mouseEnum.getWireless();
    }

    public static boolean getNextMouseSensor(MouseEnum mouseEnum) {
        return mouseEnum.getHasSensor();
    }
}

