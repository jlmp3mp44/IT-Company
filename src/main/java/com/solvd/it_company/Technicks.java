package com.solvd.it_company;


import com.solvd.it_company.Lambdas.WordProcessorTeam;
import com.solvd.it_company.Lambdas.WordProcessorTechnicks;
import com.solvd.it_company.interfaces.InfoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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
        WordProcessorTechnicks<Device, String> appendDevicesInfo = (title, devices) -> {
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
    public Optional<LapTop> getLapTopWithBiggestScreenSize(){
        Optional<LapTop> largestLapTop = lapTops.stream()
                .max(Comparator.comparingDouble(LapTop::getScreenSize));
        return largestLapTop;
    }
    public List<String> finAllWirelessMouse(){
        return mouses.stream()
                .filter(Mouse::isWireless)
                .map(mouse -> mouse.getName())
                .collect(Collectors.toList());
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
