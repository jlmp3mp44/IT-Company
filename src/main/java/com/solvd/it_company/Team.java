package com.solvd.it_company;


import com.solvd.it_company.Lambdas.WordProcessorTeam;
import com.solvd.it_company.exceptions.SizeOfTeamSmallException;
import com.solvd.it_company.interfaces.InfoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Team implements InfoInterface {

    private static final Logger LOGGER = LogManager.getLogger(Team.class);

    private final String nameOfTeam;
    private Set<Developer> developers;
    private Set<Manager> managers;
    private Set<QAEngineer> qaEngineers;

    public Team(Set<Developer> developers, Set<Manager> managers, Set<QAEngineer> qaEngineers, String nameOfTeam) {
        this.developers = new TreeSet<>(developers);
        this.managers = new TreeSet<>(managers);
        this.qaEngineers = new TreeSet<>(qaEngineers);
        this.nameOfTeam = nameOfTeam;
    }

    //get the main information about team, names and surnames of employees
    public final StringBuilder getInfo() {
        int sizeOfTeam = developers.size() + managers.size() + qaEngineers.size();
        try {
            validateSizeOfTeam(sizeOfTeam);
        } catch (SizeOfTeamSmallException e) {
            LOGGER.error(e.getMessage());
        }
        StringBuilder result = new StringBuilder();

        WordProcessorTeam<Employee, String> appendEmployeeInfo = (title, employees) -> {
            result.append("\n").append(title).append("\n");
            employees.forEach(employee -> result.append(employee.toString()).append("\n"));
        };

        appendEmployeeInfo.appendInfo("DEVELOPERS", developers);
        appendEmployeeInfo.appendInfo("MANAGERS", managers);
        appendEmployeeInfo.appendInfo("QA ENGINEERS", qaEngineers);

        return result;
    }


    public void validateSizeOfTeam(int sizeOfTeam) throws SizeOfTeamSmallException {
        if (sizeOfTeam <= 3) throw new SizeOfTeamSmallException
                ("Size of team very small, This project is not  convenient for the company");
    }


    public void writeInfoToTheFile() {
        try (FileOutputStream allEmployees = new FileOutputStream("D:\\Course_testimg\\IT-Company\\src\\main\\java\\com\\solvd" +
                "\\it_company\\files\\infoEmployees.txt")) {
            byte[] buffer = getInfo().toString().getBytes();
            allEmployees.write(buffer);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error ocured " + e.getMessage());
        }
    }
    public void sortEmployeesBySurname(){
        Comparator<Employee> surnameComparator = (e1, e2) ->
                e1.getSurname().compareToIgnoreCase(e2.getSurname());
        developers = sortEmployees(developers, surnameComparator);
        managers = sortEmployees(managers, surnameComparator);
        qaEngineers = sortEmployees(qaEngineers, surnameComparator);
    }
    private <T extends Employee> Set<T> sortEmployees(Set<T> employees, Comparator<? super T> comparator) {
        TreeSet<T> sortedSet = new TreeSet<>(comparator);
        sortedSet.addAll(employees);
        return sortedSet;
    }
    public String findEmployeeBySurname(Set<? extends Employee> employees, String surname){
        Optional<? extends Employee> matchEmployee = employees.stream()
                .filter(employee -> employee.getSurname().equals(surname))
                .findFirst();
               return matchEmployee.map(employee ->
                        String.format(employee.getName() + " " + employee.getSurname() + " " + employee.getFullSalary()))
                       .orElse("There is no employee with this surname");
    }
    public String findEmplWithBiggestSalary(Set<? extends Employee> employees){
        return employees.stream()
                .max(Comparator.comparingInt(Employee::getFullSalary))
                .map(employee -> String.format("The employee with biggest salary " + employee.getName()+ " " + employee.getSurname()
                + " " + employee.getFullSalary()))
                .orElse("There is no employee with the biggest salary");
    }


    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(TreeSet<Developer> developers) {

        this.developers = developers;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(TreeSet<Manager> managers) {

        this.managers = managers;
    }

    public Set<QAEngineer> getQaEngineers() {

        return qaEngineers;
    }

    public void setQaEngineers(TreeSet<QAEngineer> qaEngineers) {
        this.qaEngineers = qaEngineers;
    }

    public String getNameOfTeam() {
        return nameOfTeam;
    }
}
