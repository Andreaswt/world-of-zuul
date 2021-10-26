package com.company;

import java.util.ArrayList;

public class Challenge {
    private String name;
    private String description;
    private ArrayList<String> options;

    public Challenge(String name, String description, ArrayList<String> options) {
        this.name = name;
        this.description = description;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}
