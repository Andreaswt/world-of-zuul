package com.company;

import java.util.ArrayList;
import java.util.Map;

public class Challenge {
    private String name;
    private String description;
    private Map<String,String> options;
    private ArrayList<String> effect;
    private boolean hasOptions;

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
        this.hasOptions = true;

    }

    public Challenge(String name, String description, ArrayList<String> effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.hasOptions = false;
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
                case "Merge":
                    merge();
                    break;
                case "Exile":
                    killMembers();
                    break;
                case "Give":
                    removeFoodAndPeople();
                    break;
                case "Nothing":
                    break;
            }
        }
    }
    public void merge(){
        System.out.println("MERGE");
    }

    public void fight(){
        System.out.println("FIGHT");
    }

    public void flee(){
        System.out.println("FLEE");
    }

    public void killMember(){
        Group.killMember(100);
    }

    public void killMembers(){
        Group.killMember(100);
        Group.killMember(100);
    }

    public void removeFoodAndPeople(){
        System.out.println("SAGDE FOOD AND PEEPO MOMENT");
    }

    public boolean getHasOptions(){
        return this.hasOptions;
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
        this.options.forEach((k,v) -> {
            rString.add(k);
        });
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
