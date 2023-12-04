package com.solvd.it_company;

import com.solvd.it_company.enums.EmployeeEnum;
import com.solvd.it_company.enums.LapTopEnum;
import com.solvd.it_company.enums.MouseEnum;
import com.solvd.it_company.enums.PriceEnum;
import com.solvd.it_company.lambdas.CustomLogger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    static CustomLogger<String> customLogger = (message) -> LOGGER.info(message);


    public static void main(String[] args) {

        //initializing the important variables

        final Application application = new Application("AStore", 2,
                "Appliation - online store with custom clothes", 0);
        //instantiation of classes
        Customer customer = new Customer("Mariya", "Vasulivska", true, application, 15000);
        Set<String> system = new HashSet<>();
        system.add("IOS");
        Functional functional = new Functional((HashSet<String>) system, 10, true, 2);
        int tasksForEveryone = Employee.getTasksForEveryOne(functional);

        //instantiation of employees
        Set<Developer> developers = (Set<Developer>)
                makeEmployees(tasksForEveryone, Company.setNumOfDevelopes(functional), "Developer");
        Set<Manager> managers = (Set<Manager>)
                makeEmployees(tasksForEveryone, Company.setNumOfManagers(functional), "Manager");
        Set<QAEngineer> qaEngineers = (Set<QAEngineer>)
                makeEmployees(tasksForEveryone, Company.setNumOfQA(functional), "QAEngineer");

        //instantiation of devices
        int sizeOfTeam = developers.size() + managers.size() + qaEngineers.size();
        Set<LapTop> lapTops = (Set<LapTop>) makeDevices(sizeOfTeam, PriceEnum.MAX_COST_LAPTOP.getPrice(),
                PriceEnum.MIN_COST_LAPTOP.getPrice(), "LapTop");
        Set<Mouse> mouses = (Set<Mouse>) makeDevices(sizeOfTeam, PriceEnum.MAX_COST_MOUSE.getPrice(),
                PriceEnum.MIN_COST_MOUSE.getPrice(), "Mouse");

        Technicks technicks = new Technicks(lapTops, mouses);

        Team team = new Team(developers, managers, qaEngineers, "Coolers");
        Company company = new Company("Brains", team, 5, technicks);


        //calculate the cost of application
        CalculatorCost calculatorCost = new CalculatorCost(customer, functional, company);
        int cost = calculatorCost.calculateCost();
        //printing main information

        customLogger.log("PROJECT STARTS");
        customLogger.log("CUSTOMER: " + customer.getName() + " " + customer.getSurname() + " Budget: " + customer.getBudget());
        customLogger.log("APPLICATION: " + application.toString());
        customLogger.log("TEAM: " + team.getNameOfTeam().toUpperCase());
        customLogger.log("FUNCTIONAL: " + functional.toString());
        customLogger.log("The PRICE for this application will be:");
        customLogger.log(cost + "$");

        //Write info about Employees and Technicks to the file
        team.sortEmployeesBySurname();
        team.writeInfoToTheFile();
        customLogger.log("Wrote info about Team to the file infoEmployees.txt");
        technicks.writeInfoToTheFile();
        customLogger.log("Wrote info about technicks to the file infoTehnicks.txt");
        //Write info about customer to the file
        customer.writeInfoToTheFile();
        customLogger.log("Wrote info about customer to the file infoCustomer.txt");
        findNumberUniqueWords("D:/Course_testimg/IT-Company/src/main/java/com/solvd/it_company/files/starryNightAestetic.txt",
                "D:/Course_testimg/IT-Company/src/main/java/com/solvd/it_company/files/numberUniqueWords.txt");

        ///using streams
        Optional<LapTop> largestLapTop = technicks.getLapTopWithBiggestScreenSize();
        LOGGER.info("The lapTop with the biggest screen size " + largestLapTop.get().getName() + " " + largestLapTop.get().getScreenSize());
        LOGGER.info("Finding employee with surname " + "Johnson");
        LOGGER.info(team.findEmployeeBySurname(developers, "Jhonson"));
        LOGGER.info("Wireless mouses: ");
        LOGGER.info(technicks.finAllWirelessMouse());
        LOGGER.info("Manager with the biggest salary: ");
        LOGGER.info(team.findEmplWithBiggestSalary(managers));
        customLogger.log("PROJECT END \n");
    }

    //methods to creating instantiation of employees and devices

    public static Set<? extends Employee> makeEmployees(int tasks, int number, String type) {
        Set<Employee> employees = new TreeSet<>();
        for (int i = 0; i < number; i++) {
            String name = EmployeeGenerator.getNextName();
            switch (type) {
                case "Developer":
                    employees.add(new Developer(name,
                            EmployeeGenerator.getNextSurname(EmployeeEnum.valueOf(name)),
                            tasks, EmployeeGenerator.getNextLevel(EmployeeEnum.valueOf(name))));

                    break;
                case "Manager":
                    employees.add(new Manager(name,
                            EmployeeGenerator.getNextSurname(EmployeeEnum.valueOf(name)),
                            tasks, EmployeeGenerator.getNextExperience(EmployeeEnum.valueOf(name))));
                    break;
                case "QAEngineer":
                    employees.add(new QAEngineer(EmployeeGenerator.getNextName(),
                            EmployeeGenerator.getNextSurname(EmployeeEnum.valueOf(name)),
                            tasks, EmployeeGenerator.getNextLevel(EmployeeEnum.valueOf(name))));
                    break;
            }
        }
        return employees;
    }

    public static Set<? extends Device> makeDevices(int sizeOfTeam, int maxCostDevice, int minCostDevice, String device) {
        Set<Device> devices = new TreeSet<>();
        for (int i = 0; i < sizeOfTeam; i++) {
            int randomCostDevice = (int) (Math.random() * (maxCostDevice - minCostDevice + 1)) + minCostDevice;
            switch (device) {
                case "LapTop":
                    String lapTopName = DeviceGenerator.getNextDeviceName("LapTop");
                    devices.add(new LapTop(randomCostDevice, DeviceGenerator.getNextDeviceName(device),
                            DeviceGenerator.getNextLapTopScreenSize(LapTopEnum.valueOf(lapTopName)),
                            DeviceGenerator.getNextLapTopMemorySize(LapTopEnum.valueOf(lapTopName))));
                    break;
                case "Mouse":
                    String mouseName = DeviceGenerator.getNextDeviceName("Mouse");
                    devices.add(new Mouse(randomCostDevice, DeviceGenerator.getNextDeviceName(device),
                            DeviceGenerator.getNextMouseWireless(MouseEnum.valueOf(mouseName)),
                            DeviceGenerator.getNextMouseSensor(MouseEnum.valueOf(mouseName))));
                    break;
            }
        }
        return devices;
    }

    public static void findNumberUniqueWords(String filePathFrom, String filePathTo) {
        Set<String> uniqueWords;
        try {
            List<String> lines = FileUtils.readLines(new File(filePathFrom));
            uniqueWords = lines.stream()
                    .flatMap(line -> Arrays.stream(StringUtils.split(line)))
                    .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                    .filter(word -> !word.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            FileUtils.writeStringToFile(new File(filePathTo), Integer.toString(uniqueWords.size()), "UTF-8");
            LOGGER.info("Number of Unique Words has been written to the file");
        } catch (IOException e) {
            LOGGER.error("Error occured while work with files" + e.getMessage());
        }
    }
}
