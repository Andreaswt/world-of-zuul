package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Group {
    private Inventory inventory;
    private int hunger;
    private ArrayList<Person> members;

    public Group() {
        this.inventory = new Inventory();
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

    public void killMember() {
        int groupSize = this.members.size();

        // Generate random number, to remove random person member from group
        Random rand = new Random();
        int index = rand.nextInt(groupSize);

        this.members.remove(index);

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
}
