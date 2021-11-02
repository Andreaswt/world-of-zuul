package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Group {
    private static Inventory inventory;
    private static int satiety = 100;
    final static private int foodSatietyValue = 5;
    private static int food;
    private static ArrayList<Person> members = new ArrayList<>();

    public Group() {
        this.inventory = new Inventory();
        addToGroup(6);
    }

    private static void addToGroup(int toAdd) {
        // Add 6 persons
        for (int i = 0; i < toAdd; i++) {
            Person member = new Person("Person" + i);
            members.add(member);
        }

        // Add 10 food units
        food = 10;
    }

    public static void addFood(int foodToAdd) {
        food += foodToAdd;
    }

    public static void removeFood(int foodToRemove) {
        food -= foodToRemove;
        if (food < 0) { food = 0; }
    }

    public static void eat() {
        for (int i = 0; i < getGroupSize(); i++) {
            if (food > 0){
                food--;
                if (satiety < 100){
                    System.out.println("Group has eaten " + foodSatietyValue + " units of food");
                    satiety += foodSatietyValue;
                }
            }
            else{
                if (satiety > 0) {
                    satiety -= foodSatietyValue;
                }
                else{
                    System.out.println("Because of starvation, a member has been killed");
                    killMember(20);
                }
            }
        }
    }

    public static void killMember(double chanceOfDeath) {
        int groupSize = getGroupSize();

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

    public static void merge() {
        int membersToAdd = 3;
        addToGroup(membersToAdd);

        System.out.println("Groups have been merged, and you group have " + membersToAdd + " new members.");
    }

        public static void removeMember(){
        int groupSize = members.size();
        int index = 2;
        members.remove(index);
        System.out.println("Two members has been removed from the group.");
    }

    public void printStats() {
        System.out.println();
        System.out.println("---- Dine stats er ----");

        System.out.println("Mad:");
        System.out.println(food);

        System.out.println();
        System.out.println("Gruppe mæthed:");
        System.out.println(this.satiety + "%");

        System.out.println();
        System.out.println("Gruppestørrelse:");
        System.out.println((members != null) ? getGroupSize() : "Ingen medlemmer");

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

    public static int getGroupSize() {
        if (members != null) {
            return members.size();
        }
        else {
            return 0;
        }
    }

    public void setMembers(ArrayList<Person> members) {
        members = members;
    }
}
