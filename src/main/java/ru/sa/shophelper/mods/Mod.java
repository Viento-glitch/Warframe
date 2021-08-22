package ru.sa.shophelper.mods;

public class Mod {

    String name;
    int price;

    public Mod() {
    }

    public Mod(String name) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
