package com.solvd.it_company;


import com.solvd.it_company.exceptions.SizeOfTeamSmallException;
import com.solvd.it_company.interfaces.InfoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

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
    public final String getInfo() {
        int sizeOfTeam = developers.size() + managers.size() + qaEngineers.size();
        try {
            validateSizeOfTeam(sizeOfTeam);
        } catch (SizeOfTeamSmallException e) {
            LOGGER.error(e.getMessage());
        }
        String result = "";
        result += "DEVELOPERS \n";
        for (Developer developer : developers) {
            result += developer.toString() + "\n";
        }
        result += "\nMANAGERS \n";
        for (Manager manager : managers) {
            result += manager.toString() + "\n";
        }
        result += "\nQA ENGINEERS \n";
        for (QAEngineer qaEngineer : qaEngineers) {
            result += qaEngineer.toString() + "\n";
        }
        return result;
    }

    public void validateSizeOfTeam(int sizeOfTeam) throws SizeOfTeamSmallException {
        if (sizeOfTeam <= 3) throw new SizeOfTeamSmallException
                ("Size of team very small, This project is not  convenient for the company");
    }


    public void writeInfoToTheFile() {
        try (FileOutputStream allEmployees = new FileOutputStream("D:\\Course_testimg\\Course\\src\\com\\" +
                "solvd\\laba\\oop\\files\\infoEmployees.txt")) {
            byte[] buffer = getInfo().getBytes();
            allEmployees.write(buffer);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error ocured " + e.getMessage());
        }
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
