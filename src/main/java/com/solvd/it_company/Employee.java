package com.solvd.it_company;


import com.solvd.it_company.exceptions.NumOfTasksZeroOrLessException;
import com.solvd.it_company.interfaces.FullNameableInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class Employee implements FullNameableInterface, Comparable<Employee> {
    private static final Logger LOGGER = LogManager.getLogger(Employee.class);
    protected static int tasksForEveryOne;
    private final String name;
    private final String surname;
    protected static final int BASE_SALARY = 1000;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    //distribution of tasks to workers
    public static final int getTasksForEveryOne(Functional functional) {
        tasksForEveryOne = functional.getNumberOfTasks() / 5;
        try {
            validateTasksForEveryOne(tasksForEveryOne);
        } catch (NumOfTasksZeroOrLessException e) {
            LOGGER.error(e.getMessage());
        }
        return tasksForEveryOne;
    }

    public static void validateTasksForEveryOne(int tasksForEveryOne) throws NumOfTasksZeroOrLessException {
        if (tasksForEveryOne <= 0) throw new NumOfTasksZeroOrLessException
                ("Num of tasks for every employee is incorrect:" + tasksForEveryOne);
    }

    @Override
    public int compareTo(Employee o) {
        return this.getSurname().compareTo(o.getSurname());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public final int getBaseSalary() {
        return BASE_SALARY;
    }

    protected abstract int getFullSalary();
}



