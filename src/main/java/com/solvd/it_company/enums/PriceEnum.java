package com.solvd.it_company.enums;

public enum PriceEnum{
    MIN_COST_LAPTOP("MinCostLapTop", 400 ),
    MAX_COST_LAPTOP("MaxCostlapTp", 1200),
    MIN_COST_MOUSE("MinCostMouse", 50),
    MAX_COST_MOUSE("MaxCostMouse", 150);
    private String name;
    private Integer price;

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