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
        this.challenges = new ArrayList<Challenge>();
        readFromFile();
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room city, forest, cliffs, hilltops, university, club, beach, lake, fields, cornField;

        city = new Room("in an abandoned city", getRandomChallenge());
        forest = new Room("in a dark forest", getRandomChallenge());
        cliffs = new Room("at the cliffs", getRandomChallenge());
        hilltops = new Room("at the hilltops by the cliffs", getRandomChallenge());
        university = new Room("in the university", getRandomChallenge());
        club = new Room("in nightclub", getRandomChallenge());
        beach = new Room("at the beach", getRandomChallenge());
        lake = new Room("at the lake", getRandomChallenge());
        fields = new Room("at the fields", getRandomChallenge());
        cornField = new Room("in the cornfield", getRandomChallenge());

        // Abandoned city
        city.setExit("forest", forest);
        city.setExit("club", club);
        city.setExit("university", university);
        city.setExit("fields", fields);

        // Fields
        fields.setExit("cornfield", cornField);
        fields.setExit("city", city);

        // Cornfield
        cornField.setExit("fields", fields);

        // Club
        club.setExit("city", city);

        // Dark forest
        forest.setExit("city", city);
        forest.setExit("hilltops", hilltops);

        // Hilltops
        hilltops.setExit("cliffs", cliffs);
        hilltops.setExit("forest", forest);

        // Cliffs
        cliffs.setExit("hilltops", hilltops);

        // University
        university.setExit("lake", lake);
        university.setExit("city", city);

        // Lake
        lake.setExit("university", university);
        lake.setExit("beach", beach);

        // Beach
        beach.setExit("lake", lake);

        currentRoom = city;
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {

            if (this.group.getGroupSize() == 0) {
                finished = true;
                System.out.println();
                System.out.println("GAME OVER: You group have reached a number of 0.");
            }
            else {

                Command command = parser.getCommand();
                finished = processCommand(command);
            }
        }
        System.out.println("Thank you for playing. Good bye.");
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
        System.out.println("Welcome to the Climate Wars!");
        System.out.println("Climate Wars will teach you about the disastrous effects of climate change.");

        // Back story
        System.out.println();
        System.out.println("Back story: The year is 2030, due to a lack of action from the world as a whole to solve the climate crisis, a climate catastrophe has reached new highs. This has led to a total collapse of society. Billions are dead due food shortages, lack of shelter from the increasingly disastrous weather and wars fought to gather what resources that are left on earth. The survivors that are now left must roam the lands to scavenge and hunt for food and resources. You must lead a group of people through the dangerous and harsh environments. You will have to manage the needs of your group, making sure that there is enough food and making tough decisions along the way as the leader of the group. Group members will come and go as you progress, you will meet new people that may join your ranks, and you will lose people as you attempt to endure the dangers of this world. Your objective is to keep the group of survivors alive as long as possible, but eventually the climate claims us all.");
        System.out.println("Good luck survivor.");
        System.out.println();

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
        System.out.println("The climate have changed...");
        System.out.println("For the worse");
        System.out.println("Lead your group to survival.");
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
            this.currentRoom.getChallenges().applyEffect();
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
