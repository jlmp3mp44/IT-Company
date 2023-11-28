package com.solvd.it_company;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {

    static long seed;
    static Random random;
    static int minCostLapTop;
    static int maxCostLapTop;

    static int minCostMouse;
    static int maxCostMouse;
    static int randomCostDevice;


    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
        seed = 12345L;
        random = new Random(seed);
        minCostLapTop = 400;
        maxCostLapTop = 1200;
        minCostMouse = 50;
        maxCostMouse = 150;
    }


    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        //initializing the important variables

        final Application application = new Application("AStore", 2,
                "Appliation - online store with custom clothes", 0);
        //instantiation of classes
        Customer customer = new Customer("Mariya", "Vasulivska", true, application, 15000);
        Set<String> system = new HashSet<>();
        system.add("IOS");
        Functional functional = new Functional((HashSet<String>) system, 8, true, 2);
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
        LinkedList<LapTop> lapTops = makeDevices(sizeOfTeam, maxCostLapTop, minCostLapTop, "LapTop");
        LinkedList<Mouse> mouses = makeDevices(sizeOfTeam, maxCostMouse, minCostMouse, "Mouse");

        Technicks technicks = new Technicks(lapTops, mouses);

        Team team = new Team(developers, managers, qaEngineers, "Coolers");
        Company company = new Company("Brains", team, 5, technicks);


        //calculate the cost of application
        CalculatorCost calculatorCost = new CalculatorCost(customer, functional, company);
        int cost = calculatorCost.calculateCost();
        //printing main information

        LOGGER.info("PROJECT STARTS");
        LOGGER.info("CUSTOMER: " + customer.getName() + " " + customer.getSurname() + " Budget: " + customer.getBudget());
        LOGGER.info("APPLICATION: " + application.toString());
        LOGGER.info("TEAM: " + team.getNameOfTeam().toUpperCase());
        LOGGER.info("FUNCTIONAL: " + functional.toString());
        LOGGER.info("The PRICE for this application will be:");
        LOGGER.info(cost + "$");

        //Write info about Employees and Technicks to the file
        team.writeInfoToTheFile();
        LOGGER.info("Wrote info about Team to the file infoEmployees.txt");
        technicks.writeInfoToTheFile();
        LOGGER.info("Wrote info about technicks to the file infoTehnicks.txt");
        //Write info about customer to the file
        customer.writeInfoToTheFile();
        LOGGER.info("Wrote info about customer to the file infoCustomer.txt");
        findNumberUniqueWords("D:/Course_testimg/IT-Company/src/main/java/com/solvd/it_company/files/starryNightAestetic.txt",
                "D:/Course_testimg/IT-Company/src/main/java/com/solvd/it_company/files/numberUniqueWords.txt");
        LOGGER.info("PROJECT END \n");
    }

    //methods to creating instantiation of employees and devices

    public static Set<? extends Employee> makeEmployees(int tasks, int number, String type) {
        Set<Employee> employees = new TreeSet<>();
        for (int i = 0; i < number; i++) {
            switch (type) {
                case "Developer":
                    employees.add(new Developer(EmployeeGenerator.getNextName(), EmployeeGenerator.getNextSurname(),
                            tasks, EmployeeGenerator.getNextLevel()));
                    break;
                case "Manager":
                    employees.add(new Manager(EmployeeGenerator.getNextName(), EmployeeGenerator.getNextSurname(),
                            tasks, EmployeeGenerator.getNextExperience()));
                    break;
                case "QAEngineer":
                    employees.add(new QAEngineer(EmployeeGenerator.getNextName(), EmployeeGenerator.getNextSurname(),
                            tasks, EmployeeGenerator.getNextLevel()));
                    break;
            }
        }
        return employees;
    }

    public static <T extends Device> LinkedList<T> makeDevices(int sizeOfTeam, int maxCostDevice, int minCostDevice, String device) {
        LinkedList<T> devices = new LinkedList<>();
        for (int i = 0; i < sizeOfTeam; i++) {
            randomCostDevice = random.nextInt(maxCostDevice - minCostDevice + 1) + minCostDevice;
            switch (device) {
                case "LapTop":
                    devices.add((T) new LapTop(randomCostDevice, DeviceGenerator.getNextDeviceName(device),
                            DeviceGenerator.getNextLapTopScreenSize(), DeviceGenerator.getNextLapTopMemorySize()));
                    break;
                case "Mouse":
                    devices.add((T) new Mouse(randomCostDevice, DeviceGenerator.getNextDeviceName(device),
                            DeviceGenerator.getNextMouseWireless(), DeviceGenerator.getNextMouseSensor()));
                    break;
            }
        }
        return devices;
    }

    public static void findNumberUniqueWords(String filePathFrom, String filePathTo) {
        Set<String> uniqueWords;
        try {
            List<String> lines = FileUtils.readLines(new File(filePathFrom));
            uniqueWords = new HashSet<>();
            for (String line : lines) {
                String[] wordsSplit = StringUtils.split(line);
                for (String word : wordsSplit) {
                    word = word.replaceAll("[^a-zA-Z]", "");
                    if (!word.isEmpty()) {
                        uniqueWords.add(word.toLowerCase());
                    }
                }
            }
            FileUtils.writeStringToFile(new File(filePathTo), Integer.toString(uniqueWords.size()), "UTF-8");
            LOGGER.info("Number of Unique Words has been written to the file");
        } catch (IOException e) {
            LOGGER.error("Error occured while work with files" + e.getMessage());
        }
    }
}
