package com.solvd.it_company;


import com.solvd.it_company.interfaces.InfoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Technicks implements InfoInterface {
    private static final Logger LOGGER = LogManager.getLogger(Technicks.class);
    private LinkedList<LapTop> lapTops;
    private LinkedList<Mouse> mouses;

    public Technicks(LinkedList<LapTop> lapTops, LinkedList<Mouse> mouses) {
        this.lapTops = lapTops;
        this.mouses = mouses;
    }

    public LinkedList<LapTop> getLapTops() {
        return lapTops;
    }

    public void setLapTops(LinkedList<LapTop> lapTops) {
        this.lapTops = lapTops;
    }

    public LinkedList<Mouse> getMouses() {
        return mouses;
    }

    public void setMouses(LinkedList<Mouse> mouses) {
        this.mouses = mouses;
    }

    //get the main information about technicks, names of devices, and their properties
    public final String getInfo() {
        String result = "";
        result += "LapTops \n";
        for (LapTop lapTop : lapTops) {
            result += lapTop.toString() + "\n";
        }
        result += "\nMouses \n";
        for (Mouse mouse : mouses) {
            result += mouse.toString() + "\n";
        }
        return result;
    }

    public void writeInfoToTheFile() {
        try (FileOutputStream allTechnicks = new FileOutputStream("D:\\Course_testimg\\Course\\src\\com\\" +
                "solvd\\laba\\oop\\files\\infoTechnicks.txt")) {
            byte[] buffer = getInfo().getBytes();
            allTechnicks.write(buffer);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error ocured " + e.getMessage());
        }
    }

}
