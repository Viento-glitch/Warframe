package ru.sa.shophelper;

import ru.sa.shophelper.mods.Mod;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class WarfHelper {
    private static final int dailyLimit = 29000;
    static List<Mod> mods = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        makeModsList();

        int selectedSyndicate = 0;
        while (true) {
            System.out.println("Выберите действие");
            System.out.println("1.Составление торгового сообщения");
            System.out.println("2.Калькулятор Warframe");
            String text = readText();

            if (text.equals("1") || text.equals("2")) {
                if (text.equals("1")) {
                    tradeMessage();
                    break;
                } else {
                    while (true) {
                        System.out.println("Выберите синдикат для расчёта");
                        System.out.println("1.Острон");
                        System.out.println("2.Фортуна");
                        System.out.println("3.Скульптуры (фулл/сток)");
                        System.out.println("4.Венера: Тороиды крисма");
                        System.out.println("5.");
                        text = readText();

                        if (text.equals("1") || text.equals("2") || text.equals("3") || text.equals("4")) {
                            break;
                        } else {
                            System.out.println("Выбрано не верное значение.");
                        }
                    }
                }
            }

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
            if (text.equals("4")) {
                crismaCalcReputation();
                break;
            }
        }
    }

    public static List<Mod> makeModsList() {
        mods.add(new Mod("Болевая Точка Прайм", 0, "https://warframe.market/ru/items/primed_pressure_point"));
        mods.add(new Mod("Быстрохват Прайм", 0, "https://warframe.market/ru/items/primed_quickdraw"));
        mods.add(new Mod("Быстрые руки Прайм", 0, "https://warframe.market/ru/items/primed_fast_hands"));
        mods.add(new Mod("В Упор Прайм", 0, "https://warframe.market/ru/items/primed_point_blank"));
        mods.add(new Mod("Вожак Стаи Прайм", 0, "https://warframe.market/ru/items/primed_pack_leader"));
        mods.add(new Mod("Горячий Заряд Прайм", 0, "https://warframe.market/ru/items/primed_heated_charge"));
        mods.add(new Mod("Дробитель Прайм", 0, "https://warframe.market/ru/items/primed_target_cracker"));
        mods.add(new Mod("Животный Инстинкт Прайм", 0, "https://warframe.market/ru/items/primed_animal_instinct"));
        mods.add(new Mod("Заряженные Снаряды Прайм", 0, "https://warframe.market/ru/items/primed_charged_shell"));
        mods.add(new Mod("Зачистка Прайм: Гринир", 0, "https://warframe.market/ru/items/primed_cleanse_grineer"));
        mods.add(new Mod("Зачистка Прайм: Зараженные", 0, "https://warframe.market/ru/items/primed_cleanse_infested"));
        mods.add(new Mod("Зачистка Прайм: Корпус", 0, "https://warframe.market/ru/items/primed_cleanse_corpus"));
        mods.add(new Mod("Зачистка Прайм: Порабощенные", 0, "https://warframe.market/ru/items/primed_cleanse_corrupted"));
        mods.add(new Mod("Изгнание Прайм: Гринир", 0, "https://warframe.market/ru/items/primed_expel_grineer"));
        mods.add(new Mod("Изгнание Прайм: Зараженные", 0, "https://warframe.market/ru/items/primed_expel_infested"));
        mods.add(new Mod("Изгнание Прайм: Корпус", 0, "https://warframe.market/ru/items/primed_expel_corpus"));
        mods.add(new Mod("Изгнание Прайм: Порабощенные", 0, "https://warframe.market/ru/items/primed_expel_corrupted"));
        mods.add(new Mod("Инициирование Прайм", 0, "https://warframe.market/ru/items/primed_fulmination"));
        mods.add(new Mod("Крио Патроны Прайм", 0, "https://warframe.market/ru/items/primed_cryo_rounds"));
        mods.add(new Mod("Крушитель Прайм: Гринир", 0, "https://warframe.market/ru/items/primed_smite_grineer"));
        mods.add(new Mod("Крушитель Прайм: Зараженные", 0, "https://warframe.market/ru/items/primed_smite_infested"));
        mods.add(new Mod("Крушитель Прайм: Корпус", 0, "https://warframe.market/ru/items/primed_smite_corpus"));
        mods.add(new Mod("Крушитель Прайм: Порабощенные", 0, "https://warframe.market/ru/items/primed_smite_corrupted"));
        mods.add(new Mod("Ледяное Прикосновение Прайм", 0, "https://warframe.market/ru/items/primed_chilling_grasp"));
        mods.add(new Mod("Лихорадочный Удар Прайм", 0, "https://warframe.market/ru/items/primed_fever_strike"));
        mods.add(new Mod("Морф - Трансформер Прайм", 0, "https://warframe.market/ru/items/primed_morphic_transformer"));
        mods.add(new Mod("Мутатор Прайм: Винтовка", 0, "https://warframe.market/ru/items/primed_rifle_ammo_mutation"));
        mods.add(new Mod("Мутатор Прайм: Дробовик", 0, "https://warframe.market/ru/items/primed_shotgun_ammo_mutation"));
        mods.add(new Mod("Мутатор Прайм: Пистолет", 0, "https://warframe.market/ru/items/primed_pistol_ammo_mutation"));
        mods.add(new Mod("Непрерывность Прайм", 0, "https://warframe.market/ru/items/primed_continuity"));
        mods.add(new Mod("Огненный Шторм Прайм", 0, "https://warframe.market/ru/items/primed_firestorm"));
        mods.add(new Mod("Опустошение Прайм", 0, "https://warframe.market/ru/items/primed_ravage"));
        mods.add(new Mod("Пистолетный Гамбит Прайм", 0, "https://warframe.market/ru/items/primed_pistol_gambit"));
        mods.add(new Mod("Погибель Прайм: Гринир", 0, "https://warframe.market/ru/items/primed_bane_of_grineer"));
        mods.add(new Mod("Погибель Прайм: Зараженные", 0, "https://warframe.market/ru/items/primed_bane_of_infested"));
        mods.add(new Mod("Погибель Прайм: Корпус", 0, "https://warframe.market/ru/items/primed_bane_of_corpus"));
        mods.add(new Mod("Погибель Прайм: Порабощенные", 0, "https://warframe.market/ru/items/primed_bane_of_corrupted"));
        mods.add(new Mod("Поток Прайм", 0, "https://warframe.market/ru/items/primed_flow"));
        mods.add(new Mod("Размах Прайм", 0, "https://warframe.market/ru/items/primed_reach"));
        mods.add(new Mod("Регенерация Прайм", 0, "https://warframe.market/ru/items/primed_regen"));
        mods.add(new Mod("Скользящий Магазин Прайм", 0, "https://warframe.market/ru/items/primed_slip_magazine"));
        mods.add(new Mod("Тактическая Помпа Прайм", 0, "https://warframe.market/ru/items/primed_tactical_pump"));
        mods.add(new Mod("Тяжелая Травма Прайм", 0, "https://warframe.market/ru/items/primed_heavy_trauma"));
        return mods;
    }

    public static int bigPrice(int selectedSyndicate) {
        int bigPrice = 0;

        if (selectedSyndicate == 1) {
            bigPrice = 2000;
        }

        if (selectedSyndicate == 2) {
            bigPrice = 1000;
        }

        return bigPrice;
    }


    public static int mediumPrice(int selectedSyndicate) {
        int mediumPrice = 0;

        if (selectedSyndicate == 1) {
            mediumPrice = 1500;
        }

        if (selectedSyndicate == 2) {
            mediumPrice = 800;
        }

        return mediumPrice;

    }


    public static int smallPrice(int selectedSyndicate) {
        int smallPrice = 0;

        if (selectedSyndicate == 1) {
            smallPrice = 1200;
        }

        if (selectedSyndicate == 2) {
            smallPrice = 600;
        }

        return smallPrice;

    }


    public static void fish(int selectedSyndicate) throws IOException {

        System.out.println("Введите количество большой рыбы");
        int quantityBigFish = Integer.parseInt(readText());

        System.out.println("Введите количество средней рыбы");
        int quantityMediumFish = Integer.parseInt(readText());

        System.out.println("Введите количество малой рыбы");
        int quantitySmallFish = Integer.parseInt(readText());

        daysCalc(bigPrice(selectedSyndicate), quantityBigFish);
        daysCalc(mediumPrice(selectedSyndicate), quantityMediumFish);
        daysCalc(smallPrice(selectedSyndicate), quantitySmallFish);

    }

    private static void daysCalc(int price, int quantity) {

        double amountForADays = Math.floor(1.0 * dailyLimit / price);

        int fullPrice = price * quantity;

        if (amountForADays * price < dailyLimit) {
            amountForADays += 1;
        }

        double days = Math.floor(quantity / amountForADays);
        double sellLoss = (amountForADays * price) - dailyLimit;
        double totalLoss = sellLoss * days;


        if (amountForADays * price < dailyLimit) {
            amountForADays += 1;

        }
        int numOfDays = (int) days;

        String daysText = getProperStringEnding(numOfDays);
        //todo сделать сумму трёх значений рыбы
        System.out.println("==================================================");
        System.out.println("                                                 =");

        if (quantity % amountForADays == 0) {
            System.out.println("Этого хватит на " + numOfDays + " " + daysText);
        } else {
            System.out.println("Этого хватит на " + quantity / amountForADays + " " + daysText);
        }


        int intTotalLoss = (int) totalLoss;
        if (intTotalLoss == totalLoss) {
            System.out.println("Сумарная потеря при текущем лимите " + intTotalLoss);
        } else {
            System.out.println("Сумарная потеря при текущем лимите " + totalLoss);
        }
        System.out.println("Всего репутации без потерь " + price * quantity);

        if (fullPrice == fullPrice - totalLoss) {
            int fullPriceWithLoss = (int) (fullPrice - totalLoss);
            System.out.println("Всего репутации с потерями " + fullPriceWithLoss);
        } else {
            System.out.println("Всего репутации с потерями " + (fullPrice - totalLoss));
        }

        System.out.println("                                                 =");
        System.out.println("==================================================\n");

    }

    private static String getProperStringEnding(int numOfDays) {
        String daysText = "";
        if (numOfDays % 10 == 1) {
            daysText = "день";
        } else if (numOfDays % 10 == 2 || numOfDays % 10 == 3 || numOfDays % 10 == 4) {
            daysText = "дня";
        } else if (numOfDays % 10 == 5 || numOfDays % 10 == 6 ||
                numOfDays % 10 == 7 || numOfDays % 10 == 8 ||
                numOfDays % 10 == 9 || numOfDays % 10 == 0) {
            daysText = "дней";
        }

        return daysText;

    }

    public enum UserSelection {
        FULL, STOCK
    }

    public static void crismaCalcReputation() throws IOException {
        final int crismaPrice = 6000;
        System.out.println("Введите колличество тороидов крисма");
        int quantity = Integer.parseInt(readText());
        daysCalc(crismaPrice, quantity);

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

        int totalEndo = 0;
        int totalAmberStars = 0;
        int totalCyanStars = 0;
        if (fullOrStock.equals(UserSelection.FULL)) {
            for (Sculpture sculpture : sculptures) {
                System.out.println("В скульптуре " + sculpture.getName() + " = " + sculpture.getAllEndoOfThisTypeFull() + " Эндо");
                totalEndo += sculpture.getAllEndoOfThisTypeFull();
                System.out.println("Звёзд " + sculpture.getAllStarsAmberOfThisType() + " Янтарь");
                totalAmberStars += sculpture.getAllStarsAmberOfThisType();
                System.out.println("Звёзд " + sculpture.getAllStarsCyanOfThisType() + " Циан\n");
                totalCyanStars += sculpture.getAllStarsCyanOfThisType();
            }
        } else if (fullOrStock.equals(UserSelection.STOCK)) {
            for (Sculpture sculpture : sculptures) {
                System.out.println("В скульптурах " + sculpture.getName() + " = " + sculpture.getAllEndoOfThisTypeStock() + " Эндо");
                totalEndo += sculpture.getAllEndoOfThisTypeStock();
            }
        }

        System.out.println("Всего эндо с скульптур " + totalEndo);
        if (fullOrStock.equals(UserSelection.FULL)) {
            System.out.println("Всего звёзд янтарь требуется в скульптуры " + totalAmberStars);
            System.out.println("Всего звёзд циан требуется в скульптуры " + totalCyanStars);
        }
    }

    private static void tradeMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sellOrBuy;
        String product = "";
        while (true) {
            System.out.println("Моды или предметы");
            System.out.println("1.Моды");
            System.out.println("2.Предметы");
            String modsOrSubjects = readText();


            if (modsOrSubjects.equals("1")) {
                while (true) {
                    System.out.println("Введите название мода");
                    String modName = readText();

                    if (!checkName(modName)) {
                        System.out.println("Данного прайм мода не обноружено");
                    } else {
                        product = modName;
                        break;
                    }
                }

            }
            {

                System.out.println("Выберите действие введя цифру");
                System.out.println("1.Покупка");
                System.out.println("2.Продажа");
                sellOrBuy = reader.readLine();

            }
            if (sellOrBuy.equals("1") || sellOrBuy.equals("2")) {
                if (sellOrBuy.equals("1")) {
                    sellOrBuy = "Куплю";
                }

                if (sellOrBuy.equals("2")) {
                    sellOrBuy = "Продам";
                }
                break;
            }
        }

        if (product.equals("")) {
            System.out.println("Введите товар");
            product = readText();
        }
        String productReadyToSelling = "[" + product + "]";
        System.out.println("Введите цену");
        String price = readText();
        System.out.println("Желаете добавить что-нибудь ещё?");
        System.out.println("Если же нет просто нажмите Enter");
        String anythingMore = readText();

        String fullSellMessage = sellOrBuy + productReadyToSelling + ":trading::platinum:" + price + ":platinum::trading:" + anythingMore;


        if (checkLengthOfTradeMessage(fullSellMessage)) {
            System.out.println("Полученное сообщение(Уже скопировано в буфер обмена)");
            System.out.println(fullSellMessage);
            copy(fullSellMessage);
        } else {
            System.out.println("Длинна превышает допустимую " + fullSellMessage.length() + "/180");
        }
    }

    private static boolean checkName(String name) {

        boolean found = false;
        for (Mod mod : mods) {
            String findName = mod.getName();
            if (findName.equals(name)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private static void modSelling() {
        int found = 1;

        for (Mod mod : mods) {
            String selected = mod.getName();

            if (mod.getQuantity() > 0) {
                System.out.println("У вас есть на прадажу");
                System.out.println(found + ". " + selected);
                System.out.println("В колличестве " + mod.getQuantity() + " шт");
                found++;
            }
        }
    }

    private static int profit(int maxPrice, int minPrice) {

        return maxPrice - minPrice;

    }

    public static void copy(String copiedString) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(copiedString);
        clipboard.setContents(stringSelection, null);
    }

    private static boolean checkLengthOfTradeMessage(String parameter) {
        final int lengthOfParameter = parameter.length();
        return lengthOfParameter <= 180;
    }

    public static String readText() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }
}