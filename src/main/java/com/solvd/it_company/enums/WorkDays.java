package com.solvd.it_company.enums;

public enum WorkDays{
    MONDAY(100),
    TUESDAY(120),
    WEDNESDAY(120),
    THURSDAY(120),
    FRIDAY(150);
    private Integer additionalCostPerDay;

    WorkDays(Integer additionalCostPerDay) {
        this.additionalCostPerDay = additionalCostPerDay;
    }

    public Integer getAdditionalCostPerDay() {
        return additionalCostPerDay;
    }
}