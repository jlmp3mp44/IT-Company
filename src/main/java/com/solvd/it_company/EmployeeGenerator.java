package com.solvd.it_company;

import java.util.ArrayList;
import java.util.List;

public class EmployeeGenerator {

    static int nameIndex = 0;
    static int surnameIndex = 0;
    static int levelIndex = 0;
    static int experienceIndex = 0;

    protected enum Names {
        John, Mary, David, Sarah, Michael, Emily, William, Emma, James, Olivia, Benjamin, Sophia, Daniel, Mia, Aleksa, Poli
    }

    protected enum Surnames {
        Smith, Johnson, Brown, Lee, Wilson, Davis, Jones, Miller, Taylor, Anderson, White, Harris, Clark, Walker, Parker, Southy
    }

    protected static List<String> levels = new ArrayList<>();
    // protected static int[] experiences = new int[]{1, 5, 3, 4, 2, 5, 4, 2, 6, 3, 5, 6, 2, 5, 3};
    protected static List<Integer> experiences = new ArrayList<>();

    //methods to iterate the properties of employees
    static {
        levels.add("Jun");
        levels.add("Middle");
        levels.add("Senior");
        levels.add("Jun");
        levels.add("Jun");
        levels.add("Senior");
        levels.add("Miidle");
        levels.add("Middle");
        levels.add("Middle");
        levels.add("Jun");
        levels.add("Middle");
        levels.add("Senior");
        levels.add("Senior");
        levels.add("Middle");
        levels.add("Middle");

        experiences.add(1);
        experiences.add(4);
        experiences.add(5);
        experiences.add(3);
        experiences.add(2);
        experiences.add(6);
        experiences.add(5);
        experiences.add(4);
        experiences.add(3);
        experiences.add(2);
        experiences.add(1);
        experiences.add(5);
        experiences.add(5);
        experiences.add(3);
        experiences.add(4);

    }

    public static String getNextName() {
        Names[] names = Names.values();
        Names nextName = names[nameIndex];
        nameIndex++;
        return nextName.toString();
    }

    public static String getNextSurname() {
        Surnames[] surnames = Surnames.values();
        Surnames nextSurname = surnames[surnameIndex];
        surnameIndex++;
        return nextSurname.toString();
    }

    public static String getNextLevel() {
        String nextLevel = levels.get(levelIndex);
        levelIndex++;
        return nextLevel.toString();
    }

    public static int getNextExperience() {
        int nextExperience = experiences.get(experienceIndex);
        experienceIndex++;
        return nextExperience;
    }
}