package com.spectre.drinkoftheday;


public class Drink {

    private String drinkName;
    public Drink() {}
    public Drink(String drinkname) {
        this.drinkName = drinkname;
    }
    public void setDrinkName(String drinkname) {
        this.drinkName = drinkname;
    }
    public String getDrinkName() {
        return this.drinkName;
    }
}