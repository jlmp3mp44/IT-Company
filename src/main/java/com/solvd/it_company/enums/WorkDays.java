package com.solvd.it_company.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum WorkDays{
    MONDAY(100),
    TUESDAY(120),
    WEDNESDAY(120),
    THURSDAY(120),
    FRIDAY(150);
    static {
        final Logger LOGGER = LogManager.getLogger(WorkDays.class);
        int totalAdditionalCost = 0;

        for (WorkDays day : WorkDays.values()) {
            totalAdditionalCost += day.getAdditionalCostPerDay();
        }

        double averageAdditionalCost = (double) totalAdditionalCost / WorkDays.values().length;

        LOGGER.info("Average price for work day " + averageAdditionalCost);
    }
    private Integer additionalCostPerDay;

    WorkDays(Integer additionalCostPerDay) {
        this.additionalCostPerDay = additionalCostPerDay;
    }

    public Integer getAdditionalCostPerDay() {
        return additionalCostPerDay;
    }
}