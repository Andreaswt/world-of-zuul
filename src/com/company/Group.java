package com.company;

import javax.swing.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Random;

public class Group {
    private Inventory inventory;
    private int hunger;
    private static ArrayList<Person> members;

    public Group() {
        this.inventory = new Inventory();
        createDefaultGroup();
    }

    private void createDefaultGroup() {
        ArrayList<Person> defaultMembers = new ArrayList<Person>();

        // Add 6 persons
        for (int i = 0; i < 6; i++) {
            Person member = new Person("Person" + i);
            defaultMembers.add(member);
        }
        this.members = defaultMembers;
    }

    public void eat(int hungerSatisfaction) {
        /*
        hungerSatisfaction to be constrained between 0 (death) and 100 (full)
         */
        if (this.hunger + hungerSatisfaction > 100) {
            this.hunger = 100;
        }
        else {
            this.hunger += hungerSatisfaction;
        }
    }

    public static void killMember() {
        int groupSize = members.size();

        // Generate random number, to remove random person member from group
        Random rand = new Random();
        int index = rand.nextInt(groupSize);

        members.remove(index);
        System.out.println("A member has been killed");
    }

    public void printStats() {
        System.out.println();
        System.out.println("---- Dine stats er ----");

        System.out.println("Sult:");
        System.out.println(this.hunger);

        System.out.println();
        System.out.println("Gruppestørrelse:");
        System.out.println((this.members != null) ? this.members.size() : "Ingen medlemmer");

        System.out.println();
        System.out.println("Din beholdning:");
        System.out.println(this.inventory.toString());
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Person> members) {
        this.members = members;
    }
}
