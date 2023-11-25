package com.solvd.it_company;

import java.util.Objects;

public class Developer extends Employee {
    private final String level;
    private int numOfTasks;
    private int levelInt;

    public Developer(String name, String surname, int numOfTasks, String level) {
        super(name, surname);
        this.numOfTasks = numOfTasks;
        this.level = level;
    }

    public int getLevel() {
        switch (level) {
            case "Jun":
                levelInt = 1;
            case "Middle":
                levelInt = 2;
            case "Senior":
                levelInt = 3;
        }
        return levelInt;
    }


    @Override
    public int getFullSalary() {
        return getBaseSalary() + numOfTasks * 1000 + levelInt * 300;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return (numOfTasks == developer.numOfTasks) && (getName() == developer.getName()
                && (getSurname() == developer.getSurname()) && (level == developer.level));
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfTasks, getName(), getSurname(), level);
    }

    @Override
    public String toString() {
        return "Developer{" +
                " Name=" + getName() +
                " Surname=" + getSurname() +
                " Salary=" + getFullSalary() +
                " numOfTasks=" + numOfTasks +
                '}';
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
    }


}