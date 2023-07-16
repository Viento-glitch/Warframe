package ru.sa.warframeparcer.parcer;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OrdersParser {
    private static final String MY_NICK = "DeFeKT98";
    private static final int MINIMAL_PRICE_TO_RESALE = 10;
    private static Payload upgradeAndResale;
    private static Payload andoInvestment;

    private static final String MONEY_SOUND = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\sounds\\zvukovoy-effekt-kassovogo-apparata-29737.wav";
    private static final String NOT_ACTUAL_PRICE = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\sounds\\not_actual_price.wav";
    private static final String BUY_WAV = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\sounds\\buy.wav";
    private static final String SELL_WAV = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\sounds\\sell.wav";

    public static void main(String[] args) {
        setUpgradeAndResale(getPayload());
        setAndoInvestment(getPayload());
        
        List<String> ignoredPlayers = new ArrayList<>();

        while (true) {
            try {
                parsePrimeMods(ignoredPlayers);
                for (int i = 0; i < 5; i++) {
                    System.out.println();
                }
                parseItems(ignoredPlayers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void parsePrimeMods(List<String> ignoredPlayers) throws InterruptedException {
        List<Item> items = ItemManager.getMods();
        List<String> profitList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Payload payload = parseUrl(item);
            if (payload != null) {
                workWithPayload(payload, ignoredPlayers, profitList);
                if (i % 250 == 0 || i == items.size() - 1) {
                    finalMessage(profitList);
                }
            }
        }
        System.out.println("////////////////////////////////////////////////////////////////");
    }


    private static void parseItems(List<String> ignoredPlayers) throws InterruptedException {
        List<Item> items = ItemManager.getItems();
        List<String> profitList = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Payload payload = parseUrl(item);
            if (payload != null) {
                workWithPayload(payload, ignoredPlayers, profitList);
                if (i % 250 == 0 || i == items.size() - 1) {
                    finalMessage(profitList);
                }
            }
        }

    }

    private static void workWithPayload(Payload payload, List<String> ignore, List<String> profitList) {
        List<Order> orders = payload.getOrders();
        String nameOfProduct = payload.getRusNameOfProduct();
        BestOrders bestOrders = getBestOrders(ignore, orders, nameOfProduct);
        if (ordersIsActual(orders)) {
            printMessageBlock(payload, profitList, bestOrders);
            makeUpgradeAndResale(nameOfProduct, bestOrders);
            makeUpgrade(nameOfProduct, bestOrders);
            if (bestOrders.getStockBestSeller().getModRank() != null) {
                int modeRank = calcAndo(bestOrders.getStockBestSeller().getModRank());
                System.out.println("Эндо потребуется : " + modeRank);
            }
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        }
    }

    private static void printMessageBlock(Payload payload, List<String> profitList, BestOrders bestOrders) {
        System.out.println(payload.getRusNameOfProduct());
        System.out.println(payload.getFullUrlLink());
        makeMessagesToWorkInChat(payload, bestOrders);
        System.out.println("STOCK");
        System.out.println("\t\tПродавец\t\t|\t\tПокупатель");
        printBestOrders(bestOrders.getStockBestSeller(), bestOrders.getStockBestBuyer(), payload, profitList);
        printBestOrders(bestOrders.getStockSecondSeller(), bestOrders.getStockSecondBuyer(), payload, profitList);
        if (bestOrders.getFullBestSeller().getUser() != null || bestOrders.getFullBestBuyer().getUser() != null) {
            System.out.println("FULL");
        }
        printBestOrders(bestOrders.getFullBestSeller(), bestOrders.getFullBestBuyer(), payload, profitList);
        printBestOrders(bestOrders.getFullSecondSeller(), bestOrders.getFullSecondBuyer(), payload, profitList);
    }

    private static void makeUpgradeAndResale(String nameOfProduct, BestOrders bestOrders) {
        if (bestOrders.getFullBestBuyer().getPlatinum() != Integer.MAX_VALUE && bestOrders.getFullBestBuyer().getPlatinum() != Integer.MIN_VALUE && bestOrders.getStockBestSeller().getPlatinum() != Integer.MAX_VALUE
                && (bestOrders.getFullBestBuyer().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum()) > 0) {
            System.out.println("Купить, прокачать, продать существующему покупателю: " + (bestOrders.getFullBestBuyer().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum()));
            if (getUpgradeAndResale().getOrders().get(0).getPlatinum() < bestOrders.getFullBestBuyer().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum()) {
                List<Order> orderList = new ArrayList<>();
                Order order = new Order();
                order.setPlatinum(bestOrders.getFullBestBuyer().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum());
                orderList.add(0, order);
                orderList.add(1, bestOrders.getStockBestSeller());
                orderList.add(2, bestOrders.getFullBestBuyer());

                getUpgradeAndResale().setRusNameOfProduct(nameOfProduct);
                getUpgradeAndResale().setOrders(orderList);
            }
        }
    }

    private static void makeUpgrade(String nameOfProduct, BestOrders bestOrders) {
        if (bestOrders.getStockBestSeller().getPlatinum() != Integer.MAX_VALUE && bestOrders.getFullBestSeller().getPlatinum() != Integer.MAX_VALUE &&
                (bestOrders.getFullBestSeller().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum() > 0) &&
                getAndoInvestment().getOrders().get(0).getPlatinum() < bestOrders.getFullBestSeller().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum()) {
            System.out.println("купить, прокачать, продать по цене рынка: " + (bestOrders.getFullBestSeller().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum()));
            List<Order> orderList = new ArrayList<>();
            Order order = new Order();
            order.setPlatinum(bestOrders.getFullBestSeller().getPlatinum() - bestOrders.getStockBestSeller().getPlatinum());
            orderList.add(0, order);
            orderList.add(1, bestOrders.getStockBestSeller());
            orderList.add(2, bestOrders.getFullBestSeller());
            getAndoInvestment().setRusNameOfProduct(nameOfProduct);
            getAndoInvestment().setOrders(orderList);
        }
    }

    private static boolean ordersIsActual(List<Order> orders) {
        for (Order order : orders) {
            if (!order.getUser().getStatus().equals("offline")) return true;
        }
        return false;
    }

    private static void makeMessagesToWorkInChat(Payload payload, BestOrders bestOrders) {
        System.out.print("Куплю [" + payload.getRusNameOfProduct() + "] ");
        if (bestOrders.getStockBestBuyer().getPlatinum() != Integer.MIN_VALUE) {
            System.out.print("за " + bestOrders.getStockBestBuyer().getPlatinum());
        }
        System.out.print(" :platinum:\n");
        System.out.print("Продам [" + payload.getRusNameOfProduct() + "] ");
        if (bestOrders.getStockBestSeller().getPlatinum() != Integer.MAX_VALUE) {
            System.out.print("за " + bestOrders.getStockBestSeller().getPlatinum());
        }
        System.out.print(" :platinum:\n");
    }

    private static int calcAndo(int i) {
        int[] ando = new int[]{40920, 40880, 40800, 40640, 40320, 39680, 38400, 35840, 30720, 20480, 0};
        return ando[i];
    }


    @NotNull
    private static BestOrders getBestOrders(List<String> ignorePlayers, List<Order> orders, String nameOfProduct) {
        BestOrders bestOrders = makeBestOrders();
        List<Order> myOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().getIngameName().equals(getMyNick())) {
                myOrders.add(order);
            }
        }

        for (Order order : orders) {
            if (!ignorePlayers.contains(order.getUser().getIngameName()) && order.getPlatform().equals("pc") && !order.getUser().getStatus().equals("offline")) {
                if (order.getOrderType().equals("sell")) {
                    if (order.getModRank() != null && order.getModRank() == 10) {
                        if (order.getPlatinum() <= bestOrders.getFullBestSeller().getPlatinum()) {
                            bestOrders.setFullSecondSeller(bestOrders.getFullBestSeller());
                            bestOrders.setFullBestSeller(order);
                        }
                    } else if (order.getPlatinum() <= bestOrders.getStockBestSeller().getPlatinum()) {
                        bestOrders.setStockSecondSeller(bestOrders.getStockBestSeller());
                        bestOrders.setStockBestSeller(order);
                    }
                } else if (order.getOrderType().equals("buy")) {
                    if (order.getModRank() != null && order.getModRank() == 10) {
                        if (order.getPlatinum() >= bestOrders.getFullBestBuyer().getPlatinum()) {
                            bestOrders.setFullSecondBuyer(bestOrders.getFullBestBuyer());
                            bestOrders.setFullBestBuyer(order);
                        }
                    } else if (order.getPlatinum() >= bestOrders.getStockBestBuyer().getPlatinum()) {
                        bestOrders.setStockSecondBuyer(bestOrders.getStockBestBuyer());
                        bestOrders.setStockBestBuyer(order);
                    }
                }
            }
        }
        checkMyOrders(nameOfProduct, bestOrders, myOrders);
        return bestOrders;
    }

    private static void checkMyOrders(String nameOfProduct, BestOrders bestOrders, List<Order> myOrders) {
        for (Order order : myOrders) {
            if (order.getOrderType().equals("sell")) {
                if (order.getModRank() != null && order.getModRank() == 10) {
                    if (!bestOrders.getFullBestSeller().equals(order)) {
                        Sound.playSound(getSellWav()).join();

                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Перебили цену продажи фулла");
                        System.out.println(nameOfProduct);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        if (bestOrders.getFullSecondSeller().getPlatinum() - order.getPlatinum() > 5) {
                            Sound.playSound(getNotActualPrice()).join();

                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("НЕ АКТУАЛЬНАЯ ЦЕНА В ПРОДАЖЕ");
                            System.out.println(nameOfProduct);
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                        }
                    }
                } else {
                    if (!bestOrders.getStockBestSeller().equals(order)) {
                        Sound.playSound(getSellWav()).join();

                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Перебили цену продажи стока");
                        System.out.println(nameOfProduct);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        if (bestOrders.getStockSecondSeller().getPlatinum() - order.getPlatinum() > 5) {
                            Sound.playSound(getNotActualPrice()).join();

                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("НЕ АКТУАЛЬНАЯ ЦЕНА В ПРОДАЖЕ");
                            System.out.println(nameOfProduct);
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                        }
                    }
                }
            } else {
                if (order.getModRank() != null && order.getModRank() == 10) {
                    if (!bestOrders.getFullBestBuyer().equals(order)) {
                        Sound.playSound(getBuyWav()).join();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Перебили цену покупки фулла");
                        System.out.println(nameOfProduct);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        if (order.getPlatinum() - bestOrders.getFullSecondBuyer().getPlatinum() > 5) {
                            Sound.playSound(getNotActualPrice()).join();

                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("НЕ АКТУАЛЬНАЯ ЦЕНА В ПОКУПКЕ");
                            System.out.println(nameOfProduct);
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                    }
                } else {
                    if (!bestOrders.getStockBestBuyer().equals(order)) {
                        Sound.playSound(getBuyWav()).join();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Перебили цену покупки стока");
                        System.out.println(nameOfProduct);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        if (order.getPlatinum() - bestOrders.getStockSecondBuyer().getPlatinum() > 5) {
                            Sound.playSound(getNotActualPrice()).join();

                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("НЕ АКТУАЛЬНАЯ ЦЕНА В ПОКУПКЕ");
                            System.out.println(nameOfProduct);
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                    }
                }
            }
        }
    }

    private static BestOrders makeBestOrders() {
        Order stockBestSeller = new Order();
        stockBestSeller.setPlatinum(Integer.MAX_VALUE);
        Order stockSecondSeller = new Order();
        stockSecondSeller.setPlatinum(Integer.MAX_VALUE);
        Order stockBestBuyer = new Order();
        stockBestBuyer.setPlatinum(Integer.MIN_VALUE);
        Order stockSecondBuyer = new Order();
        stockSecondBuyer.setPlatinum(Integer.MIN_VALUE);


        Order fullBestSeller = new Order();
        fullBestSeller.setPlatinum(Integer.MAX_VALUE);
        Order fullSecondSeller = new Order();
        fullSecondSeller.setPlatinum(Integer.MAX_VALUE);
        Order fullBestBuyer = new Order();
        fullBestBuyer.setPlatinum(Integer.MIN_VALUE);
        Order fullSecondBuyer = new Order();
        fullSecondBuyer.setPlatinum(Integer.MIN_VALUE);

        return new BestOrders(
                stockBestSeller,
                stockSecondSeller,
                stockBestBuyer,
                stockSecondBuyer,
                fullBestSeller,
                fullSecondSeller,
                fullBestBuyer,
                fullSecondBuyer);
    }

    private static void finalMessage(List<String> profitList) {
        printResales(profitList);

        if (getUpgradeAndResale().getOrders().get(0).getPlatinum() != 0) {
            System.out.println("Быстрая прокачка и продажа");
            System.out.println(getUpgradeAndResale().getRusNameOfProduct());
            System.out.println("На руки:" + (getUpgradeAndResale().getOrders().get(2).getPlatinum() - getUpgradeAndResale().getOrders().get(1).getPlatinum()) + "\n");
        }
        if (getAndoInvestment().getOrders().get(0).getPlatinum() != 0) {
            System.out.println("Лучшая инвестиция эндо");
            System.out.println(getAndoInvestment().getRusNameOfProduct());
            System.out.println("На руки:" + (getAndoInvestment().getOrders().get(2).getPlatinum() - getAndoInvestment().getOrders().get(1).getPlatinum()));
        }
    }

    private static void printResales(List<String> profitList) {
        if (!profitList.isEmpty()) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("перепродажи");
            for (String profit : profitList) {
                System.out.println(profit);
            }
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }
    }

    @NotNull
    private static Payload getPayload() {
        Payload payload = new Payload();
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setPlatinum(0);
        orderList.add(order);
        payload.setOrders(orderList);
        return payload;
    }

    private static void printBestOrders(Order sellOrder, Order buyOrder, Payload payload, List<String> profitList) {
        String buyer = "";
        String buyerRegion = "";
        String sellerRegion = "";
        if (sellOrder.getRegion() != null && buyOrder.getRegion() != null) {
            if (!buyOrder.getRegion().equals("en")) buyerRegion = " (" + buyOrder.getRegion() + ") ";
            if (!sellOrder.getRegion().equals("en")) sellerRegion = " (" + sellOrder.getRegion() + ") ";
        }
        if (sellOrder.getPlatinum() != Integer.MIN_VALUE && sellOrder.getPlatinum() != Integer.MAX_VALUE) {
            if (buyOrder.getPlatinum() != Integer.MAX_VALUE && buyOrder.getPlatinum() != Integer.MIN_VALUE) {
                buyer = " |\t( " + buyOrder.getPlatinum() + " )\t" + buyerRegion + buyOrder.getUser().getIngameName();
            }
            System.out.println(sellOrder.getUser().getIngameName() + sellerRegion + "\t( " + sellOrder.getPlatinum() + " )\t" + buyer);
            if (buyOrder.getPlatinum() != Integer.MAX_VALUE && buyOrder.getPlatinum() != Integer.MIN_VALUE &&
                    sellOrder.getPlatinum() != Integer.MAX_VALUE && sellOrder.getPlatinum() != Integer.MIN_VALUE &&
                    buyOrder.getPlatinum() - sellOrder.getPlatinum() >= getMinimalPriceToResale()) {

                if (buyOrder.getModRank() != null && buyOrder.getModRank() != 0) {

                    System.out.println("Апгрейд и продажа:" + (buyOrder.getPlatinum() - sellOrder.getPlatinum()) + " " + buyOrder.getModRank() + " ранга");
                    System.out.println("Нужно эндо" + calcAndo(buyOrder.getModRank() - sellOrder.getModRank()));
                } else {
                    System.out.println("Быстрая перепродажа: " + (buyOrder.getPlatinum() - sellOrder.getPlatinum()));
                    if (buyOrder.getModRank() != null) {
                        System.out.print(" " + buyOrder.getModRank() + " ранга ");
                    }
                    System.out.println();
                    generatePrivateMessage(sellOrder, payload);
                    generatePrivateMessage(buyOrder, payload);
                    System.out.println();
                    Sound.playSound(getMoneySound()).join();
                    profitList.add(payload.getRusNameOfProduct());
                    printResales(profitList);
                }
            }
        }
    }

    private static void generatePrivateMessage(Order order, Payload payload) {
        String modeRank = "";
        if (order.getModRank() != null) {
            modeRank = order.getModRank().toString();
        }
        if (!order.getOrderType().equals("sell")) {
            System.out.println("sell");
            if (order.getRegion().equals("ru")) {
                System.out.println("/w " + order.getUser().getIngameName() + " Привет! я хочу продать: " + payload.getRusNameOfProduct() + " (ранг " + modeRank + ") за " + order.getPlatinum() + " платины. (warframe.market)");
            } else {
                System.out.println("/w " + order.getUser().getIngameName() + " Hi! I want to sell: " + payload.getEngNameOfProduct() + " (rank " + modeRank + ") for " + order.getPlatinum() + " platinum. (warframe.market)");
            }
        } else {
            System.out.println("buy");
            if (order.getRegion().equals("ru")) {
                System.out.println("/w " + order.getUser().getIngameName() + " Привет! я хочу купить: " + payload.getRusNameOfProduct() + " (ранг " + modeRank + ") за " + order.getPlatinum() + " платины. (warframe.market)");
            } else {
                System.out.println("/w " + order.getUser().getIngameName() + " Hi! I want to buy: " + payload.getEngNameOfProduct() + " (rank " + modeRank + ") for " + order.getPlatinum() + " platinum. (warframe.market)");
            }
        }
    }

    private static Payload parseUrl(Item item) throws InterruptedException {
        String url = getUrlFromItem(item);
        HttpClient httpClient = new HttpClient();
        Payload payload;
        try {
            String json = httpClient.readJsonFromUrl(url);
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonObject root = jsonParser.parse(json).getAsJsonObject().getAsJsonObject();
            payload = gson.fromJson(root.getAsJsonObject().get("payload"), Payload.class);
            if (!payload.getOrders().isEmpty()) {
                payload.setFullUrlLink("https://warframe.market/ru/items/" + item.getUrlName());
                payload.setRusNameOfProduct(item.getRu().getItemName());
                payload.setEngNameOfProduct(item.getEn().getItemName());
                payload.setFranceNameOfProduct(item.getFr().getItemName());
                payload.setGermanyNameOfProduct(item.getDe().getItemName());
                payload.setKoreaNameOfProduct(item.getKo().getItemName());
                return payload;
            }
        } catch (Exception e) {
            Thread.sleep(100);
        }
        return null;
    }

    private static String getUrlFromItem(Item item) {
        return "https://api.warframe.market/v1/items/" + item.getUrlName() + "/orders";
    }

    public static Payload getUpgradeAndResale() {
        return upgradeAndResale;
    }

    public static void setUpgradeAndResale(Payload upgradeAndResale) {
        OrdersParser.upgradeAndResale = upgradeAndResale;
    }

    public static Payload getAndoInvestment() {
        return andoInvestment;
    }

    public static void setAndoInvestment(Payload andoInvestment) {
        OrdersParser.andoInvestment = andoInvestment;
    }

    public static String getMyNick() {
        return MY_NICK;
    }

    public static int getMinimalPriceToResale() {
        return MINIMAL_PRICE_TO_RESALE;
    }

    public static String getMoneySound() {
        return MONEY_SOUND;
    }

    public static String getNotActualPrice() {
        return NOT_ACTUAL_PRICE;
    }

    public static String getBuyWav() {
        return BUY_WAV;
    }

    public static String getSellWav() {
        return SELL_WAV;
    }
}

