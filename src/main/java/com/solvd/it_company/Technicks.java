package com.solvd.it_company;


import com.solvd.it_company.Lambdas.WordProcessor;
import com.solvd.it_company.interfaces.InfoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;


public class Technicks implements InfoInterface {
    private static final Logger LOGGER = LogManager.getLogger(Technicks.class);
    private Set<LapTop> lapTops;
    private Set<Mouse> mouses;

    public Technicks(Set<LapTop> lapTops, Set<Mouse> mouses) {
        this.lapTops = lapTops;
        this.mouses = mouses;
    }

    //get the main information about technicks, names of devices, and their properties
    public final StringBuilder getInfo() {
        StringBuilder result = new StringBuilder();
        WordProcessor appendDevicesInfo = (title, devices) -> {
            result.append("\n").append(title).append("\n");
            devices.forEach(device -> result.append(device.toString()).append("\n"));
        };
        appendDevicesInfo.appendInfo("LapTops", lapTops);
        appendDevicesInfo.appendInfo("Mouses", mouses);
        return result;
    }

    public void writeInfoToTheFile() {
        try (FileOutputStream allTechnicks = new FileOutputStream("D:\\Course_testimg\\IT-Company\\src\\main\\java" +
                "\\com\\solvd\\it_company\\files\\infoTechnicks.txt")) {
            byte[] buffer = getInfo().toString().getBytes();
            allTechnicks.write(buffer);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error ocured " + e.getMessage());
        }
    }

    public Set<LapTop> getLapTops() {
        return lapTops;
    }

    public void setLapTops(Set<LapTop> lapTops) {
        this.lapTops = lapTops;
    }

    public Set<Mouse> getMouses() {
        return mouses;
    }

    public void setMouses(Set<Mouse> mouses) {
        this.mouses = mouses;
    }
}
