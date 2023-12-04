package com.solvd.it_company;

import com.solvd.it_company.enums.WorkDays;
import com.solvd.it_company.exceptions.CostApplicationExpensiveException;
import com.solvd.it_company.exceptions.PriceDeviceZeroOrLessException;
import com.solvd.it_company.exceptions.SalaryZeroOrLessException;
import com.solvd.it_company.interfaces.CalculatorCostInterface;
import com.solvd.it_company.lambdas.MonthsRefactorToDays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

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

    // Sum salary every employee
    public int calculateAllSalary() {
        int totalSalary = 0;
        Function<Integer, Integer> allSalary = (salary) -> {
            salary += calculateSalary(getDevelopers(), "Developes");
            salary += calculateSalary(getManagers(), "Manager");
            salary += calculateSalary(getQaEngineers(), "QA Engineer");
            return salary;

        };
        totalSalary = allSalary.apply(totalSalary);

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

    public int calculateCostDevices(Set<? extends Device> devices, String category) {
        return devices.stream()
                .mapToInt(device -> {
                    try {
                        validateCOstDevices(device, category);
                    } catch (PriceDeviceZeroOrLessException e) {
                        LOGGER.error(e.getMessage());
                    }
                    return device.getCost();
                })
                .sum();
    }

    public void validateCOstDevices(Device device, String category) throws PriceDeviceZeroOrLessException {
        if (device.getCost() <= 0) throw new PriceDeviceZeroOrLessException
                ("The price one of the  " + category + " is null");
    }

    //calculate the full cost of application
    public int calculateCost() {
        int fullCost = 0;
        int additionalCost = 0;
        Function<Integer, Integer> addAdditionalCost = (cost) -> {
            cost += WorkDays.MONDAY.getAdditionalCostPerDay();
            cost += WorkDays.TUESDAY.getAdditionalCostPerDay();
            cost += WorkDays.WEDNESDAY.getAdditionalCostPerDay();
            cost += WorkDays.THURSDAY.getAdditionalCostPerDay();
            cost += WorkDays.FRIDAY.getAdditionalCostPerDay();
            return cost;
        };
        fullCost = addAdditionalCost.apply(fullCost);

        MonthsRefactorToDays<Integer, Integer, Integer> timeRef = (month, days) -> month * days;
        int time = timeRef.monthsToDays(customer.getApplication().getTimeToMake(), 30);
        int complexity = functional.getComplexityApp();
        int system = functional.getSystem().size();
        int numOfTasks = functional.getNumberOfTasks();
        int mediaContent = functional.isMediaContent() ? 2 : 0;
        int discount = customer.isRegularCustomer() ? 7 : 0;
        double percantageCompany = company.getPercentageOfAmount();

        int fullSalary = calculateAllSalary();
        int fullCostDevices = calculateCostDevices();

        BiFunction<Integer, Integer, Integer> addTwoIntegerCosts = (num1, num2) -> num1 + num2;
        int costEmployeesAndDevices = addTwoIntegerCosts.apply(fullSalary, fullCostDevices);

        BiFunction<Integer, Double, Double> addTwoDiffCosts = (num1, num2) -> num1 + num2;
        double costFunctional = system * complexity * 10;
        double costFuncAndEmplDev = addTwoDiffCosts.apply(costEmployeesAndDevices / 2, costFunctional);

        double costWithoutPercantage = costFuncAndEmplDev + (complexity * time) + (numOfTasks * mediaContent);

        double costWithDiscount = costWithoutPercantage - costWithoutPercantage * discount / 100;
        fullCost = (int) (costWithDiscount + costWithDiscount * percantageCompany / 100) + additionalCost;
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

    public Set<LapTop> getLapTops() {
        return getTechnicks().getLapTops();
    }

    public Set<Mouse> getMouses() {
        return getTechnicks().getMouses();
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


}
