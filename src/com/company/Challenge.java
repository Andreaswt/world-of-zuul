package com.company;

import java.util.ArrayList;
import java.util.function.IntConsumer;


public class Challenge {
    private String name;
    private String description;
    private ArrayList<String> options;
    private String effect;

    public Challenge(String name){
        this.name = name;
    }

    public Challenge(String name, String description, ArrayList<String> options, String effect) {
        this.name = name;
        this.description = description;
        this.options = options;

    }

    public Challenge(String name, String description, String effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;

    }

    public void applyEffect(){
        switch (effect){
            case "Kill member":
                killMember();
                break;
            case "Reduce food gain":
                reduceFoodGain();
                break;
        }
    }

    public void killMember(){
        System.out.println("KILL");
    }

    public void reduceFoodGain(){
        System.out.println("SAGDE FOOD MOMENT");
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

    @Override
    public String toString(){
        return this.name + "\n" + this.description + "\n" + this.effect;
    }
}
