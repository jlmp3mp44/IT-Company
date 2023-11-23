package com.solvd.it_company;

import java.util.Objects;

public class QAEngineer extends Employee {
    private String level;
    private int numOfTasks;
    private int levelInt;

    public QAEngineer(String name, String surname, int numOfTasks, String level) {
        super(name, surname);
        this.numOfTasks = numOfTasks;
        this.level = level;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
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

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public int getFullSalary() {
        return getBaseSalary() + numOfTasks * 1000 + levelInt * 300;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QAEngineer qaEngineer = (QAEngineer) o;
        return (numOfTasks == qaEngineer.numOfTasks) && (getName() == qaEngineer.getName()
                && (getSurname() == qaEngineer.getSurname()) && (level == qaEngineer.level));
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfTasks, getName(), getSurname(), level);
    }

    @Override
    public String toString() {
        return "QAEngineer{" +
                " Name=" + getName() +
                " Surname=" + getSurname() +
                " Salary=" + getFullSalary() +
                " numOfTasks=" + numOfTasks +
                '}';
    }
}

