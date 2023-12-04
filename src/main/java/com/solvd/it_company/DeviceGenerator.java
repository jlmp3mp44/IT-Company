package com.solvd.it_company;

import com.solvd.it_company.enums.LapTopEnum;
import com.solvd.it_company.enums.MouseEnum;

public class DeviceGenerator {
    private static int lapTopIndex = 0;
    private static int mouseIndex = 0;

    //methods to iterate the properties of  devices
    public static String getNextDeviceName(String device) {
        switch (device) {
            case "LapTop":
                LapTopEnum[] lapTopNames = LapTopEnum.values();
                LapTopEnum nextLapTopName = lapTopNames[lapTopIndex];
                lapTopIndex++;
                return nextLapTopName.toString();

            case "Mouse":
                MouseEnum[] mouseNames = MouseEnum.values();
                MouseEnum nextMouseName = mouseNames[mouseIndex];
                mouseIndex++;
                return nextMouseName.toString();
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
        return mouseEnum.getWireless();
    }

    public static boolean getNextMouseSensor(MouseEnum mouseEnum) {
        return mouseEnum.getHasSensor();
    }
}

