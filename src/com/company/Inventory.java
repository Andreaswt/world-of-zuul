package com.company;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {}

    // Overloaded constructor to added initial item
    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void printItems() {
        System.out.println("Your inventory contains");
        System.out.println(this.items.toString());
    }
}
