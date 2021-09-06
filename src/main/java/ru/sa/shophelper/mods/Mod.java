package ru.sa.shophelper.mods;

public class Mod {

    String name;
    int price;
    int quantity;

    String link;

    public Mod(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getLink() {
        return link;
    }

    public Mod(String name, int quantity, String link) {
        this.name = name;
        this.quantity = quantity;
        this.link = link;
    }

    public int getQuantity() {
        return quantity;
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
