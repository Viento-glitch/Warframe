package ru.sa.warfcalc;

public class Sculpture {

    private String name;
    private int endoFull;
    private int endoStock;
    private int ayatanAmberStar;
    private int ayatanCyanStar;
    private int quantity;

    public Sculpture(String name, int endoFull, int endoStock, int ayatanAmberStar, int ayatanCyanStar, int quantity) {
        this.name = name;
        this.endoFull = endoFull;
        this.endoStock = endoStock;
        this.ayatanAmberStar = ayatanAmberStar;
        this.ayatanCyanStar = ayatanCyanStar;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getAllEndoOfThisTypeFull() {
        return endoFull * quantity;
    }

    public int getAllEndoOfThisTypeStock() {
        return endoStock * quantity;
    }

    public int getAllStarsAmberOfThisType() {
        return ayatanAmberStar * quantity;
    }

    public int getAllStarsCyanOfThisType() {
        return ayatanCyanStar * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
