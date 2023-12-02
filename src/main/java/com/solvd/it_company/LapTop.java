package com.solvd.it_company;

import java.util.Objects;

public class LapTop extends Device {
    private final double screenSize;
    private final int memorySize;

    public LapTop(int cost, String name, double screenSize, int memorySize) {
        super(cost, name);
        this.screenSize = screenSize;
        this.memorySize = memorySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LapTop lapTop = (LapTop) o;
        return (getCost() == lapTop.getCost()) && (getName() == lapTop.getName() &&
                (getScreenSize() == lapTop.getScreenSize()) && (getMemorySize() == lapTop.getMemorySize()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCost(), getName(), getScreenSize());
    }

    @Override
    public String toString() {
        return "LapTop{" +
                " Name=" + getName() +
                " Cost=" + getCost() +
                " ScreenSize=" + getScreenSize() +
                " MemorySize=" + getMemorySize() +
                '}';
    }

    public double getScreenSize() {
        return screenSize;
    }

    public int getMemorySize() {
        return memorySize;
    }
}
