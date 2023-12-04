package com.solvd.it_company.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    static {
        final Logger LOGGER = LogManager.getLogger(LapTopEnum.class);
        int totalscreenSize = 0;
        int lapTopCount = 0;

        for (LapTopEnum lapTopEnum : LapTopEnum.values()) {
            totalscreenSize += lapTopEnum.getScreenSize();
            lapTopCount++;
        }

        double averageScreenSize = (double) totalscreenSize / lapTopCount;
        LOGGER.info("Average level of experience " +  averageScreenSize);
    }
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