package com.company;

import java.util.ArrayList;


public class Challenge {
    private String name;
    private String description;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<String> effect;

    public Challenge(String name){
        this.name = name;
    }

    public Challenge(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Challenge(String name, String description, ArrayList<ArrayList<String>> options,  ArrayList<String> effect) {
        this.name = name;
        this.description = description;
        this.options = options;
        this.effect = effect;

    }

    public Challenge(String name, String description, ArrayList<String> effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;

    }

    public void applyEffect(){
        for(String s : this.effect) {
            switch (s) {
                case "Kill member":
                    killMember();
                    break;
                case "Remove people and food":
                    removeFoodAndPeople();
                    break;
                case "Fight":
                    fight();
                    break;
                case "Flee":
                    flee();
                    break;
                case "Kill members":
                    killMembers();
                    break;
            }
        }
    }
    public void fight(){ }

    public void flee(){ }

    public void killMember(){
        Group.killMember();
    }

    public void killMembers(){}

    public void removeFoodAndPeople(){
        System.out.println("SAGDE FOOD AND PEEPO MOMENT");
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

    public ArrayList<ArrayList<String>> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<ArrayList<String>> options) {
        this.options = options;
    }

    @Override
    public String toString(){
        String s;
        s = this.name + "\n" + this.description;

        if(this.options != null) {
            s += "\n" + "Your options are:" + "\n";
            for (ArrayList<String> aS : this.options) {
                s += "\n";
                s += aS.get(0);
            }
        }
        return s;

    }
}
