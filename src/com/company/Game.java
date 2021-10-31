package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    private ArrayList<Challenge> challenges;
    private Parser parser;
    private Room currentRoom;
    private Group group;
        

    public Game() 
    {
        this.group = new Group();

        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void readFromFile(){
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

    private Challenge getRandomChallenge(){

        Random rand = new Random();
        int index = rand.nextInt(this.challenges.size());
        return this.challenges.get(index);

    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.STATS) {
            group.printStats();
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;

            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
