package com.solvd.it_company.enums;

public enum EmployeeEnum {
    JOHN("John", "Smith", "Jun", 4),
    MARY("Mary", "Jhonson", "Middle", 4),
    DAVID("David","Brown", "Senior", 5),
    SARAH("Sarah","Lee", "Jun", 1),
    MICHAEL("Michael","Wilson", "Jun", 1),
    EMILY("Emily","Davis", "Jun", 1),
    WILLAM("William","Jones", "Senior", 6),
    EMMA("Emma","Miller", "Senior", 4),
    JAMES("James","Taylor", "Jun", 3),
    OLIVIA("Olivia","Anerson", "Middle", 4),
    BENJAMIN("Benjamin","White", "Middle", 4),
    SOPHIA("Sophia","Harris", "Middle", 5),
    DANIEL("Daniel","Clark", "Jun", 2),
    MIA("Mia","Walker", "Jun", 2),
    ALEKSA("Aleksa","Parker", "Jun", 2),
    POLINA("Polina","Sothu", "Jun", 2);

    private String name;
    private String surname;
    private String level;
    private Integer experience;
    EmployeeEnum(String name, String surname, String level, Integer experience){
        this.name =  name;
        this.surname =  surname;
        this.level =  level;
        this.experience =  experience;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public Integer getExperience() {
        return experience;
    }
}

