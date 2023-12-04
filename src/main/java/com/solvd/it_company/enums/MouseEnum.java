package com.solvd.it_company.enums;

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

