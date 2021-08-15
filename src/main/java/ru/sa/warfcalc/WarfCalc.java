package ru.sa.warfcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


class WarfCalc {
    private static int sumAllSyndicateOfFish;
    private static int dailyLimit;

    public static void main(String[] args) throws IOException {

        dailyLimit = 29000;
        int selectedSyndicate = 0;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Выберите синдикат для расчёта");
            System.out.println("1.Острон");
            System.out.println("2.Фортуна");
            System.out.println("3.Скульптуры (фулл/сток)");
            String text = bufferedReader.readLine();

            if (text.equals("1")) {
                selectedSyndicate = 1;
            }
            if (text.equals("2")) {
                selectedSyndicate = 2;
            }
            if (selectedSyndicate == 1 || selectedSyndicate == 2) {
                fish(selectedSyndicate);
                break;
            }

            if (text.equals("3")) {
                System.out.println("Выберите вид скульптуры");
                System.out.println("1. Фулл");
                System.out.println("2. Сток");
                text = readText();

                int fullOrStock = Integer.parseInt(text);
                if (fullOrStock != 1 && fullOrStock != 2) {
                    System.out.println("Выбрано не верное значение");
                } else {
                    UserSelection userSelection = fullOrStock == 1
                            ? UserSelection.FULL
                            : UserSelection.STOCK;

                    sculptureCalc(userSelection);
                    break;
                }
            }
        }
    }

    public static int bigSumPrice(int quantity, int selectedSyndicate) {
        int bigPrice = 0;

        if (selectedSyndicate == 1) {
            bigPrice = 2000;
        }

        if (selectedSyndicate == 2) {
            bigPrice = 1000;
        }

        return bigPrice * quantity;
    }


    public static int mediumSumPrice(int quantity, int selectedSyndicate) {
        int mediumPrice = 0;

        if (selectedSyndicate == 1) {
            mediumPrice = 1500;
        }

        if (selectedSyndicate == 2) {
            mediumPrice = 800;
        }

        return mediumPrice * quantity;

    }


    public static int smallSumPrice(int quantity, int selectedSyndicate) {
        int smallPrice = 0;

        if (selectedSyndicate == 1) {
            smallPrice = 1200;
        }

        if (selectedSyndicate == 2) {
            smallPrice = 600;
        }

        return smallPrice * quantity;

    }


    public static void fish(int selectedSyndicate) throws IOException {

        int sumAllSyndicateOfFish = 0;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите количество большой рыбы");
        int quantityBigFish = Integer.parseInt(bufferedReader.readLine());
        sumAllSyndicateOfFish = bigSumPrice(quantityBigFish, selectedSyndicate);

        System.out.println("Введите количество средней рыбы");
        int quantityMediumFish = Integer.parseInt(bufferedReader.readLine());
        sumAllSyndicateOfFish += mediumSumPrice(quantityMediumFish, selectedSyndicate);

        System.out.println("Введите количество малой рыбы");
        int quantitySmallFish = Integer.parseInt(bufferedReader.readLine());
        sumAllSyndicateOfFish += smallSumPrice(quantitySmallFish, selectedSyndicate);

        System.out.println("Синтеза " + sumAllSyndicateOfFish);
        int stockForDays = sumAllSyndicateOfFish / dailyLimit;
        String days = "";
        if (stockForDays % 10 == 1) {
            days = "день";
        } else if (stockForDays % 10 == 2 || stockForDays % 10 == 3 || stockForDays % 10 == 4) {
            days = "дня";
        } else if (stockForDays % 10 == 5 || stockForDays % 10 == 6 || stockForDays % 10 == 7 || stockForDays % 10 == 8 || stockForDays % 10 == 9 || stockForDays % 10 == 0) {
            days = "дней";
        }
        System.out.println("этого хватит на " + stockForDays + " " + days);

    }

    //todo создать метод sculpture в котором будет реализованны следующие действия

    //todo исправить сетер количества

    public enum UserSelection {
        FULL, STOCK
    }

    public static void sculptureCalc(UserSelection fullOrStock) throws IOException {
        List<Sculpture> sculptures = new ArrayList<>();
        sculptures.add(new Sculpture("Анаса", 3450, 2000, 2, 2, 0));
        sculptures.add(new Sculpture("Орта", 2700, 650, 1, 3, 0));
        sculptures.add(new Sculpture("Вайа", 1800, 400, 1, 2, 0));
        sculptures.add(new Sculpture("Пив", 1725, 325, 1, 2, 0));
        sculptures.add(new Sculpture("Валана", 1575, 325, 1, 2, 0));
        sculptures.add(new Sculpture("Сак", 1500, 300, 1, 2, 0));
        sculptures.add(new Sculpture("Эр", 1425, 325, 0, 3, 0));

        for (Sculpture sculpture : sculptures) {
            String name = sculpture.getName();
            System.out.println("Введите количество скульптур " + name);
            String text = readText();
            int quantity = Integer.parseInt(text);
            sculpture.setQuantity(quantity);
        }

        int fullEndo = 0;
        int fullAmberStars = 0;
        int fullCyanStars = 0;
        if (fullOrStock.equals(UserSelection.FULL)) {
            for (Sculpture sculpture : sculptures) {
                System.out.println("В скульптуре " + sculpture.getName() + " = " + sculpture.getAllEndoOfThisTypeFull() + " Эндо");
                fullEndo += sculpture.getAllEndoOfThisTypeFull();
                System.out.println("Звёзд " + sculpture.getAllStarsAmberOfThisType() + " Янтарь");
                fullAmberStars += sculpture.getAllStarsAmberOfThisType();
                System.out.println("Звёзд " + sculpture.getAllStarsCyanOfThisType() + " Циан\n");
                fullCyanStars += sculpture.getAllStarsCyanOfThisType();
            }
        } else if (fullOrStock.equals(UserSelection.STOCK)) {
            for (Sculpture sculpture : sculptures) {
                System.out.println("В скульптурах " + sculpture.getName() + " = " + sculpture.getAllEndoOfThisTypeStock() + " Эндо");
                fullEndo += sculpture.getAllEndoOfThisTypeStock();
            }
        }

        System.out.println("Всего эндо с скульптур " + fullEndo);
        if (fullOrStock.equals(UserSelection.FULL)) {
            System.out.println("Всего звёзд янтарь требуется в скульптуры " + fullAmberStars);
            System.out.println("Всего звёзд циан требуется в скульптуры " + fullCyanStars);
        }
    }

    public static String readText() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }
}