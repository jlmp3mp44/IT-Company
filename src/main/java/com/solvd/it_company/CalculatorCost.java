package com.solvd.it_company;

import com.solvd.it_company.exceptions.CostApplicationExpensiveException;
import com.solvd.it_company.exceptions.PriceDeviceZeroOrLessException;
import com.solvd.it_company.exceptions.SalaryZeroOrLessException;
import com.solvd.it_company.interfaces.CalculatorCostInterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public final class CalculatorCost implements CalculatorCostInterface {
    private static final Logger LOGGER = LogManager.getLogger(CalculatorCost.class);
    private Customer customer;
    private Functional functional;
    private Company company;


    public CalculatorCost(Customer customer, Functional functional, Company company) {
        this.customer = customer;
        this.functional = functional;
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Application getApplication() {
        return customer.getApplication();
    }

    public Functional getFunctional() {
        return functional;
    }

    public void setFunctional(Functional functional) {
        this.functional = functional;
    }

    public Team getTeam() {
        return company.getTeam();
    }

    public Set<Developer> getDevelopers() {
        return getTeam().getDevelopers();
    }

    public Set<Manager> getManagers() {
        return getTeam().getManagers();
    }

    public Set<QAEngineer> getQaEngineers() {
        return getTeam().getQaEngineers();
    }

    public Technicks getTechnicks() {
        return company.getTechnicks();
    }

    public LinkedList<LapTop> getLapTops() {
        return getTechnicks().getLapTops();
    }

    public LinkedList<Mouse> getMouses() {
        return getTechnicks().getMouses();
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Sum salary every employee
    public int calculateAllSalary() {
        int totalSalary = 0;
        totalSalary += calculateSalary(getDevelopers(), "Developer");
        totalSalary += calculateSalary(getManagers(), "Manager");
        totalSalary += calculateSalary(getQaEngineers(), "QA Engineer");

        return totalSalary;
    }

    private int calculateSalary(Set<? extends Employee> employees, String category) {
        return employees.stream()
                .mapToInt(employee -> {
                    try {
                        validateSalary(employee, category);
                    } catch (SalaryZeroOrLessException e) {
                        LOGGER.error(e.getMessage());
                    }
                    return employee.getFullSalary();
                })
                .sum();
    }

    public void validateSalary(Employee employee, String category) throws SalaryZeroOrLessException {
        if (employee.getFullSalary() <= 0) throw new SalaryZeroOrLessException
                ("The salary one of " + category + " = null");
    }


    //sum all cost devices
    public int calculateCostDevices() {
        int costLapTops = calculateCostDevices(getLapTops(), "LapTops");
        int costMouses = calculateCostDevices(getMouses(), "Mouses");
        return costLapTops + costMouses;
    }

    public int calculateCostDevices(LinkedList<? extends Device> devices, String category) {
        int costDevice = 0;

        for (Device device : devices) {
            try {
                validateCOstDevices(device, category);
            } catch (PriceDeviceZeroOrLessException e) {
                LOGGER.error(e.getMessage());
            }
            costDevice += device.getCost();
        }
        return costDevice;
    }

    public void validateCOstDevices(Device device, String category) throws PriceDeviceZeroOrLessException {
        if (device.getCost() <= 0) throw new PriceDeviceZeroOrLessException
                ("The price one of the  " + category + " is null");
    }

    //calculate the full cost of application
    public int calculateCost() {
        int fullCost = 0;
        int time = customer.getApplication().getTimeToMake();
        int complexity = functional.getComplexityApp();
        int system = functional.getSystem().size();
        int numOfTasks = functional.getNumberOfTasks();
        int mediaContent = functional.isMediaContent() ? 2 : 0;
        int discount = customer.isRegularCustomer() ? 7 : 0;
        double percantageCompany = company.getPercentageOfAmount();

        int fullSalary = calculateAllSalary();
        int fullCostDevices = calculateCostDevices();


        double costWithoutPercantage = (fullSalary + fullCostDevices) / 2 + (complexity * time) -
                (system * complexity * 10) + (numOfTasks * mediaContent);
        double costWithDiscount = costWithoutPercantage - costWithoutPercantage * discount / 100;
        fullCost = (int) (costWithDiscount + costWithDiscount * percantageCompany / 100);
        try {
            validateBudgetAndPrice(fullCost);
        } catch (CostApplicationExpensiveException e) {
            LOGGER.error(e.getMessage());
        }
        return fullCost;
    }

    public void validateBudgetAndPrice(int cost) throws CostApplicationExpensiveException {
        if (customer.getBudget() < cost) {
            throw new CostApplicationExpensiveException
                    ("Customer hasn`t enough money for this application. Price high.");
        }
    }


}
