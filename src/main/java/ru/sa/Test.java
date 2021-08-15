package ru.sa;


public class Test {
    public static void main(String[] args) {
        int dayLimit = 29000;
        int toroid = 50;
        int price = 6000;


        int fullPrice = price * toroid;
        double amountForADay = Math.floor(dayLimit / price);

        if (amountForADay * price < dayLimit) {
            amountForADay += 1;
        }

        double days = Math.floor(toroid / amountForADay);
        double sellLoss = (amountForADay * price) - dayLimit;
        double totalLoss = sellLoss * days;
        System.out.println("Полная стоимость "+fullPrice);
        System.out.println("Суммарная потеря "+totalLoss);
    }
}
