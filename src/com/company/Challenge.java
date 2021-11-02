package com.company;

import java.util.ArrayList;
import java.util.Map;

public class Challenge {
    private String name;
    private String description;
    private Map<String,String> options;
    private ArrayList<String> effect;

    public Challenge(String name){
        this.name = name;
    }

    public Challenge(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Challenge(String name, String description, Map<String,String> options) {
        this.name = name;
        this.description = description;
        this.options = options;

    }

    public Challenge(String name, String description, ArrayList<String> effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    public void applyEffect(){
        if(this.effect != null){
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
    }

    public void applyEffect(String option){
        if(this.options.containsKey(option)) {
            String effect = this.options.get(option);
            switch (effect) {
                case "Kill member":
                case "Exile":
                    killMember();
                    break;
                case "Remove people and food":
                case "Give":
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
                case "Merge":
                    merge();
                    break;
                case "Nothing":
                    break;
            }
        }
    }
    public void merge(){
        Group.merge();
    }

    public void fight(){
        // Get group size of opponents. Constant for now
        int opponentSize = 3;

        // Fight member by member, until all members have fought.
        // Each member have a 50% chance of dying during fight
        System.out.println("Your group will fight against " + opponentSize + " opponents, with a 50/50 chance of dying during fight");
        for (int i = 0; i < opponentSize; i++) {
            Group.killMember(50);
        }

        // Gain food for fighting, otherwise there's no reason to fight over fleeing
        System.out.println("You stole 10 units of food from your opponents while fighting them");
        Group.addFood(10);
    }

    public void flee(){
        System.out.println("You fled. Now, continue your journey...");
    }

    public void killMember(){
        Group.killMember(100);
    }

    public void killMembers(){
        Group.killMember(100);
        Group.killMember(100);
    }

    public void removeFoodAndPeople(){
        System.out.println("10 units of food have been removed");
        Group.removeFood(10);
        Group.killMember(100);
        Group.killMember(100);
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
        ArrayList<String> rString = new ArrayList<String>();
        if (this.options != null) {
            this.options.forEach((k,v) -> {
                rString.add(k);
            });
        }
        return rString;
    }

    public void setOptions(Map<String,String> options) {
        this.options = options;
    }

    public void setOptions(String k, String v){
        this.options.put(k,v);
    }

    @Override
    public String toString(){
        String s;
        s = "\n" + this.name + "\n" + this.description;

        if(this.options != null) {
            s += "\n" + "\n" + "Your options are:" + "\n";

            for (String aS : getOptions()) {
                s += "\n";
                s += "- "+aS;
            }
        }
        return s;
    }
}
