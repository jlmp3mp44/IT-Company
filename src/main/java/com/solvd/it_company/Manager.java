package com.solvd.it_company;

import java.util.Objects;

public class Manager extends Employee {
    private int numOfEmployees;
    private final int experience;

    public Manager(String name, String surname, int numOfDevelopers, int experience) {

        super(name, surname);
        this.numOfEmployees = numOfDevelopers;
        this.experience = experience;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }


    @Override
    public int getFullSalary() {
        return getBaseSalary() + numOfEmployees * 1000 + experience * 200;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return (numOfEmployees == manager.numOfEmployees) && (getName() == manager.getName()
                && (getSurname() == manager.getSurname()) && (experience == manager.experience));
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfEmployees, getName(), getSurname(), experience);
    }

    @Override
    public String toString() {
        return "Manager{" +
                " Name=" + getName() +
                " Surname=" + getSurname() +
                " Salary=" + getFullSalary() +
                " numOfEmployees in control=" + numOfEmployees +
                '}';
    }

}
