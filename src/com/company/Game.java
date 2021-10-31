package com.company;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class Game
{
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
