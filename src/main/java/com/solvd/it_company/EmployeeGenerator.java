package com.solvd.it_company;

import com.solvd.it_company.enums.EmployeeEnum;

public class EmployeeGenerator {

    static int nameIndex = 0;


    public static String getNextName() {
        EmployeeEnum[] names = EmployeeEnum.values();
        EmployeeEnum nextName = names[nameIndex];
        nameIndex++;
        return nextName.toString();
    }

    public static String getNextSurname(EmployeeEnum name) {
        return name.getSurname();
    }

    public static String getNextLevel(EmployeeEnum name) {
        return name.getLevel();
    }

    public static int getNextExperience(EmployeeEnum name) {
        return name.getExperience();
    }
}