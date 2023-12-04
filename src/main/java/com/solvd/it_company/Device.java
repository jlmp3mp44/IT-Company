package com.solvd.it_company;


import com.solvd.it_company.interfaces.CostableInterface;
import com.solvd.it_company.interfaces.NameableInterface;

public abstract class Device implements NameableInterface, CostableInterface, Comparable<Device> {
    protected int cost;
    protected String name;

    public Device(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    @Override
    public int compareTo(Device o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
