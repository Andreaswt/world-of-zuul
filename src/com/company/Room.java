package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private Challenge challenges;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();

    }

    public Room(String description, ArrayList<Item> items, Challenge challenges)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.items = items; // Add default items to room upon instantiation
        this.challenges = challenges;
    }

    public Room(String description, Challenge challenges)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.challenges = challenges;
    }

    public Room(String description, ArrayList<Item> items)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.items = items; // Add default items to room upon instantiation
    }

    public void removeChallenge(){
        challenges = null;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        if(this.challenges == null)
            return "You are " + description + ".\n" + getExitString();
        else
            return "You are " + description + ".\n" + getExitString()+  "\n" + this.challenges;
    }

    private String getExitString()
    {
        String returnString = "\n\033[3mWhere would you like to go?\033[0m\n";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += "[\033[1m" + exit + "\033[0m] ";
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public Challenge getChallenges() {
        return this.challenges;
    }

    public void setChallenges(Challenge challenges) {
        this.challenges = challenges;
    }
}

