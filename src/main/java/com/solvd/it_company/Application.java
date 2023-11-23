package com.solvd.it_company;

import com.solvd.it_company.interfaces.NameableInterface;

public class Application implements NameableInterface {

    private final String name;
    private final int timeToMake;//in months
    private final String description;

    public Application(String name, int timeToMake, String description, int cost) {
        this.name = name;
        this.timeToMake = timeToMake;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }


    public int getTimeToMake() {
        return timeToMake;
    }


    public String getDecription() {
        return description;
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                " timeToMake=" + timeToMake + " months" +
                " description='" + description + '\'' +
                '}';
    }
}
