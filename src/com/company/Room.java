package com.company;

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

        try {
            File myObj = new File("src/file.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.contains("$")){
                    if(data.contains("Title:")){
                        String cName;
                        cName = data.replace("Title: ", "");

                        data = myReader.nextLine();
                        String cOptions = data;



                        while(!data.contains("Effect")){
                            data = myReader.nextLine();
                        }
                        String cEffect = "";
                        int e = Integer.parseInt(data.replace("Effect: ",""));
                        for (int i = 0; i < e; i++) {
                            data = myReader.nextLine();
                            cEffect = data;
                        }
                        this.challenges.add(new Challenge(cName, cOptions, cEffect));
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

