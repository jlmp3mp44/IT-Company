package com.solvd.it_company;

import java.util.Objects;

public class Mouse extends Device {
    private final boolean wireless;
    private final boolean hasSensor;

    public Mouse(int cost, String name, boolean wireless, boolean hasSensor) {
        super(cost, name);
        this.wireless = wireless;
        this.hasSensor = hasSensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return (getCost() == mouse.getCost()) && (getName() == mouse.getName()
                && (isWireless() == mouse.isWireless()) && (hasSensor == mouse.hasSensor));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCost(), getName(), isWireless(), hasSensor);
    }

    @Override
    public String toString() {
        return "Mouse{" +
                " Name=" + getName() +
                " Cost=" + getCost() +
                " Is Wireless?=" + isWireless() +
                " Has Sensor?=" + hasSensor +
                '}';
    }

    public boolean isWireless() {
        return wireless;
    }

    public boolean isHasSensor() {
        return hasSensor;
    }
}
