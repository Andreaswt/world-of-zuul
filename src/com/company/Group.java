package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Group {
    private Inventory inventory;
    private int satiety = 100;
    final private int foodSatietyValue = 5;
    private int food;
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

        // Add 10 food units
        this.food = 10;
    }

    public void eat() {
        for (int i = 0; i < members.size(); i++) {
            if (food > 0){
                food--;
                if (satiety < 100){
                    satiety += foodSatietyValue;
                }
            }
            else{
                if (satiety > 0) {
                    satiety -= foodSatietyValue;
                }
                else{
                    killMember(20);
                }
            }
        }
    }

    public static void killMember(double chanceOfDeath) {
        int groupSize = members.size();

        // Generate random number, to remove random person member from group and a random number to roll for death
        Random rand = new Random();
        double rollForDeath = rand.nextDouble() * 100;

        if(chanceOfDeath < rollForDeath){
            return;
        }

        int index = rand.nextInt(groupSize);

        members.remove(index);
        System.out.println("A member has been killed");
    }

    public void printStats() {
        System.out.println();
        System.out.println("---- Dine stats er ----");

        System.out.println("Mad:");
        System.out.println(this.food);

        System.out.println();
        System.out.println("Gruppe mæthed:");
        System.out.println(this.satiety + "%");

        System.out.println();
        System.out.println("Gruppestørrelse:");
        System.out.println((this.members != null) ? this.members.size() : "Ingen medlemmer");

        System.out.println();
        System.out.println("Din beholdning:");
        System.out.println(this.inventory.toString());
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
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

    public int getGroupSize() {
        if (this.members != null) {
            return this.members.size();
        }
        else {
            return 0;
        }

    }

    public void setMembers(ArrayList<Person> members) {
        this.members = members;
    }
}
