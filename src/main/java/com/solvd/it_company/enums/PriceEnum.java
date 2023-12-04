package com.solvd.it_company.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PriceEnum{
    MIN_COST_LAPTOP("MinCostLapTop", 400 ),
    MAX_COST_LAPTOP("MaxCostlapTp", 1200),
    MIN_COST_MOUSE("MinCostMouse", 50),
    MAX_COST_MOUSE("MaxCostMouse", 150);
    private String name;
    private Integer price;
    static {
        final Logger LOGGER = LogManager.getLogger(PriceEnum.class);
        int totalCost = 0;

        for (PriceEnum priceEnum : PriceEnum.values()) {
            totalCost += priceEnum.getPrice();
        }

        double averageCost = (double) totalCost / WorkDays.values().length;

        LOGGER.info("Approximate cost than need for device " + averageCost);
    }
    PriceEnum(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}