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
    private ArrayList<Challenge> challenges;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.challenges = new ArrayList<Challenge>();

        //Read file
        try {
            File myObj = new File("src/Challenges.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.contains("$")){
                    if(data.contains("Title:")){
                        String cName;
                        cName = data.replace("Title: ", "");

                        data = myReader.nextLine();
                        String cDescription = data;


                        data = myReader.nextLine();
                        if(data.contains("Effect")){

                            ArrayList<String> cEffect = new ArrayList<String>();
                            int e = Integer.parseInt(data.replace("Effect: ",""));
                            for (int i = 0; i < e; i++) {
                                data = myReader.nextLine();
                                cEffect.add(data);
                            }
                            this.challenges.add(new Challenge(cName, cDescription, cEffect));
                        }
                        if(data.contains("Options")){
                            ArrayList<ArrayList<String>> cOptions = new ArrayList<ArrayList<String>>();
                            ArrayList<String> cEffect = new ArrayList<String>();
                            int e = Integer.parseInt(data.replace("Options: ",""));
                            for (int i = 0; i < e; i++) {
                                data = myReader.nextLine();
                                ArrayList<String> cOption = new ArrayList<String>();
                                cOption.add(data.replace(data.substring(data.lastIndexOf(": ") + 1), ""));
                                cOption.add(data.substring(data.lastIndexOf(": ") + 1));
                                cEffect.add(data.substring(data.lastIndexOf(": ") + 1));
                                cOptions.add(cOption);
                            }
                            this.challenges.add(new Challenge(cName, cDescription, cOptions, cEffect));
                        }

                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Room(String description, ArrayList<Item> items, ArrayList<Challenge> challenges)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.items = items; // Add default items to room upon instantiation
        this.challenges = challenges;
    }

    public Room(String description, ArrayList<Item> items)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.items = items; // Add default items to room upon instantiation
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
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(ArrayList<Challenge> challenges) {
        this.challenges = challenges;
    }
}

