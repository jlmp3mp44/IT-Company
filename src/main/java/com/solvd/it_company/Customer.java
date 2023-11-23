package com.solvd.it_company;

import com.solvd.it_company.interfaces.FullNameableInterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class Customer implements FullNameableInterface {

    private static final Logger LOGGER = LogManager.getLogger(Customer.class);

    private final String name;
    private final String surname;
    private boolean regularCustomer;
    private Application application;
    private final int budget;

    public Customer(String name, String surname, boolean regularCustomer, Application application, int budget) {
        this.name = name;
        this.surname = surname;
        this.regularCustomer = regularCustomer;
        this.application = application;
        this.budget = budget;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public boolean isRegularCustomer() {
        return regularCustomer;
    }

    public void setRegularCustomer(boolean regularCustomer) {
        this.regularCustomer = regularCustomer;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public int getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Name='" + name + '\n' +
                " Surname='" + surname + '\n' +
                " RegularCustomer=" + regularCustomer + '\n' +
                " Application=" + application + '\n' +
                " Customer`s budget= " + budget + '\n' +
                '}';
    }

    public void writeInfoToTheFile() {
        try (FileOutputStream customer = new FileOutputStream("D:\\Course_testimg\\Course\\src\\com\\" +
                "solvd\\laba\\oop\\files\\infoCustomer.txt")) {
            byte[] buffer = toString().getBytes();
            customer.write(buffer);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error occured " + e.getMessage());
        }
    }
}
