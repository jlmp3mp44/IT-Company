package com.solvd.it_company.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    static {
        final Logger LOGGER = LogManager.getLogger(MouseEnum.class);
        int wirelessCount = 0;
        int sensorCount = 0;
        for (MouseEnum mouse : MouseEnum.values()) {
            if (mouse.getWireless()) {
                wirelessCount++;
            }
            if (mouse.getHasSensor()) {
                sensorCount++;
            }
        }
        LOGGER.info("Wireless mouses " + wirelessCount);
        LOGGER.info("Sensor mouses " + sensorCount);
    }

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

