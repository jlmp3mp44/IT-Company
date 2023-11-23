package com.solvd.it_company;


import java.util.HashSet;
import java.util.Set;

public class Functional {
    private Set<String> system = new HashSet<>();
    private int numberOfTasks;
    private boolean mediaContent;
    private int complexityApp;

    public Functional(HashSet<String> system, int numberOfTasks, boolean mediaContent, int complexityApp) {
        this.system = system;
        this.numberOfTasks = numberOfTasks;
        this.mediaContent = mediaContent;
        this.complexityApp = complexityApp;
    }

    public Set<String> getSystem() {
        return system;
    }

    public void setSystem(HashSet<String> system) {
        this.system = system;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public boolean isMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(boolean mediaContent) {
        this.mediaContent = mediaContent;
    }

    public int getComplexityApp() {
        return complexityApp;
    }

    public void setComplexityApp(int complexityApp) {
        this.complexityApp = complexityApp;
    }

    @Override
    public String toString() {
        return "Functional{" +
                " system=" + system.toString() +
                " numberOfTasks=" + numberOfTasks +
                " mediaContent=" + mediaContent +
                " complexityApp=" + complexityApp +
                '}';
    }
}
