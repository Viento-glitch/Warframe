package ru.sa;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;
import ru.sa.shophelper.Sound;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersParser {
    public static void main(String[] args) {
//        executeParsing();
//        List<File> fileList = makeListOfFilesFromItemsDirectory();
//        Map<String, String> urlMap = makeMap(fileList);
//        for (int i = 0; i < urlMap.size(); i++) {
//            Payload payload = getPayload(urlMap, i);
//            System.out.println(payload.getNameOfProduct());
//        }

        while (true) {
            try {
                parsePrimeMods();
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parsePrimeMods() {
        List<Item> primeMods = PrimeModeManager.getItems();
        String pathToResults = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\resultsOfMods\\";
        List<Payload> payloads = parseUrls(primeMods, pathToResults);
        System.out.println("Вам будет выдан список лучших ордеров прайм модов");
        parsePayloads(payloads);
    }

    private static void parsePayloads(List<Payload> payloads) {
        Payload modeForeFastUpgradeAndSell = getPayload();
        Payload modeForeUpgradeAndLongSell = getPayload();
        List<String> profitList = new ArrayList<>();
        for (int i = 0; i < payloads.size(); i++) {
            List<Order> orders = payloads.get(i).getOrders();

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
            for (int j = 0, ordersSize = orders.size(); j < ordersSize; j++) {
                Order order = orders.get(j);
                if (order.getOrderType().equals("sell") && order.getPlatform().equals("pc") && !order.getUser().getStatus().equals("offline")) {
                    if (order.getModRank() == 10) {
                        if (order.getPlatinum() <= fullBestSeller.getPlatinum()) {
                            fullSecondSeller = fullBestSeller;
                            fullBestSeller = order;
                        }
                    } else if (order.getPlatinum() <= stockBestSeller.getPlatinum()) {
                        stockSecondSeller = stockBestSeller;
                        stockBestSeller = order;
                    }
                } else if (order.getOrderType().equals("buy") && order.getPlatform().equals("pc") && !order.getUser().getStatus().equals("offline")) {
                    if (order.getModRank() == 10) {
                        if (order.getPlatinum() >= fullBestBuyer.getPlatinum()) {
                            fullSecondBuyer = fullBestBuyer;
                            fullBestBuyer = order;
                        }
                    } else if (order.getPlatinum() >= stockBestBuyer.getPlatinum()) {
                        stockSecondBuyer = stockBestBuyer;
                        stockBestBuyer = order;
                    }
                }
            }
            int[] ando = new int[]{40920, 40880, 40800, 40640, 40320, 39680, 38400, 35840, 30720, 20480, 0};

            System.out.println("_______________________________________________________________");
            System.out.println(i + ")" + payloads.get(i).getNameOfProduct());
            System.out.println("STOCK");
            System.out.println("\t\tПродавец\t\t|\t\tПокупатель");
            boolean profit = false;
            if (printResultLine(stockBestSeller, stockBestBuyer)) {
                profit = true;
            }
            if (printResultLine(stockSecondSeller, stockSecondBuyer)) {
                profit = true;
            }
            System.out.println("FULL");
            System.out.println("\t\tПродавец\t|\tПокупатель");
            if (printResultLine(fullBestSeller, fullBestBuyer)) {
                profit = true;
            }
            if (printResultLine(fullSecondSeller, fullSecondBuyer)) {
                profit = true;
            }
            if (profit) {
                profitList.add(payloads.get(i).getNameOfProduct());
            }
            if (fullBestBuyer.getPlatinum() != Integer.MAX_VALUE && fullBestBuyer.getPlatinum() != Integer.MIN_VALUE && stockBestSeller.getPlatinum() != Integer.MAX_VALUE
                    && (fullBestBuyer.getPlatinum() - stockBestSeller.getPlatinum()) > 0) {
                System.out.println("Купить, прокачать, продать существующему покупателю: " + (fullBestBuyer.getPlatinum() - stockBestSeller.getPlatinum()));
                if (modeForeFastUpgradeAndSell.getOrders() == null || modeForeFastUpgradeAndSell.getOrders().get(0).getPlatinum() < fullBestBuyer.getPlatinum() - stockBestSeller.getPlatinum()) {
                    Order order = new Order();
                    order.setPlatinum(fullBestBuyer.getPlatinum() - stockBestSeller.getPlatinum());
                    modeForeFastUpgradeAndSell.setNameOfProduct(payloads.get(i).getNameOfProduct());
                    modeForeFastUpgradeAndSell.getOrders().add(0, order);

                    modeForeFastUpgradeAndSell.getOrders().add(1, stockBestSeller);
                    modeForeFastUpgradeAndSell.getOrders().add(2, fullBestBuyer);
                }
            }


            if (stockBestSeller.getPlatinum() != Integer.MAX_VALUE && fullBestSeller.getPlatinum() != Integer.MAX_VALUE) {
                if (fullBestSeller.getPlatinum() - stockBestSeller.getPlatinum() > 0) {
                    System.out.println("купить, прокачать, продать по цене рынка: " + (fullBestSeller.getPlatinum() - stockBestSeller.getPlatinum()));
                    if (modeForeUpgradeAndLongSell.getOrders() == null || modeForeUpgradeAndLongSell.getOrders().get(0).getPlatinum() < fullBestSeller.getPlatinum() - stockBestSeller.getPlatinum()) {
                        Order order = new Order();
                        order.setPlatinum(fullBestSeller.getPlatinum() - stockBestSeller.getPlatinum());
                        modeForeUpgradeAndLongSell.setNameOfProduct(payloads.get(i).getNameOfProduct());
                        modeForeUpgradeAndLongSell.getOrders().add(0, order);

                        modeForeUpgradeAndLongSell.getOrders().add(1, stockBestSeller);
                        modeForeUpgradeAndLongSell.getOrders().add(2, fullBestSeller);
                    }
                }
            }

            if (stockBestSeller.getModRank() != null) {
                System.out.println("Эндо потребуется:" + ando[stockBestSeller.getModRank()]);
            } else {
                System.out.println("Эндо потребуется:" + ando[0]);
            }
            System.out.println("_______________________________________________________________");
        }
        if (!profitList.isEmpty()) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Перепродажи");
            for (int i = 0; i < profitList.size(); i++) {
                System.out.println(profitList.get(i));
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
        }
        System.out.println("Быстрая прокачка и продажа");
        System.out.println(modeForeFastUpgradeAndSell.getNameOfProduct());
        System.out.println("На руки:" + (modeForeFastUpgradeAndSell.getOrders().get(2).getPlatinum() - modeForeFastUpgradeAndSell.getOrders().get(1).getPlatinum())+"\n");
        System.out.println("Лучшая инвестиция эндо");
        System.out.println(modeForeUpgradeAndLongSell.getNameOfProduct());
        System.out.println(modeForeUpgradeAndLongSell.getOrders().get(2).getPlatinum() - modeForeUpgradeAndLongSell.getOrders().get(1).getPlatinum());

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

    private static boolean printResultLine(Order sellOrder, Order buyOrder) {
        String buyer = "";
        if (sellOrder.getPlatinum() != Integer.MIN_VALUE && sellOrder.getPlatinum() != Integer.MAX_VALUE) {
            if (buyOrder.getPlatinum() != Integer.MAX_VALUE && buyOrder.getPlatinum() != Integer.MIN_VALUE) {
                buyer = " |\t( " + buyOrder.getPlatinum() + " )\t" + buyOrder.getUser().getIngameName();
            }
            System.out.println(sellOrder.getUser().getIngameName() + "\t( " + sellOrder.getPlatinum() + " )\t" + buyer);
            if (buyOrder.getPlatinum() != Integer.MAX_VALUE && buyOrder.getPlatinum() != Integer.MIN_VALUE &&
                    sellOrder.getPlatinum() != Integer.MAX_VALUE && sellOrder.getPlatinum() != Integer.MIN_VALUE &&
                    buyOrder.getPlatinum() - sellOrder.getPlatinum() >= 20) {

                int[] ando = new int[]{40920, 40880, 40800, 40640, 40320, 39680, 38400, 35840, 30720, 20480, 0};
                if (buyOrder.getModRank() != 0 || buyOrder.getModRank() != null) {
                    System.out.println("Апгрейд и продажа:" + (buyOrder.getPlatinum() - sellOrder.getPlatinum()) + " " + buyOrder.getModRank() + " ранга");
                    System.out.println("Нужно эндо" + ando[buyOrder.getModRank() - sellOrder.getModRank()]);
                } else {
                    System.out.println("Быстрая перепродажа:" + (buyOrder.getPlatinum() - sellOrder.getPlatinum()) + buyOrder.getModRank() + " ранга");
                    Sound.playSound("C:\\Users\\Viento\\Downloads\\wfm-items-master\\zvukovoy-effekt-kassovogo-apparata-29737.wav").join();
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Payload> parseUrls(List<Item> primeMods, String pathToResults) {
        List<String> urls = getUrlListFrom(primeMods);
        HttpClient httpClient = new HttpClient();

        List<Payload> payloads = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            try {
                String json = httpClient.readJsonFromUrl(urls.get(i));
                Gson gson = new Gson();
                JsonParser jsonParser = new JsonParser();
                JsonObject root = jsonParser.parse(json).getAsJsonObject().getAsJsonObject();
                Payload payload = gson.fromJson(root.getAsJsonObject().get("payload"), Payload.class);
                payload.setNameOfProduct(primeMods.get(i).getRu().getItemName());
                if (payload.getOrders() == null) {
                } else payloads.add(payload);
                Thread.sleep(150);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return payloads;
    }

    private static List<String> getUrlListFrom(List<Item> primeMods) {
        List<String> resultList = new ArrayList<>();
        String path = "https://api.warframe.market/v1/items/";
        for (int i = 0; i < primeMods.size(); i++) {
            resultList.add(path + primeMods.get(i).getUrlName() + "/orders");
        }
        return resultList;
    }

    private static void executeParsing() throws IOException {
        List<File> fileList = makeListOfFilesFromItemsDirectory();
        Map<String, String> urlMap = makeMap(fileList);

        for (int i = 0; i < urlMap.size(); i++) {
            Payload payload = getPayload(urlMap, i);
            String nameOfObjectOnRus = payload.getNameOfProduct();
            int minSellPrice = Integer.MAX_VALUE;
            String nickInGame = "";
            for (int j = 0; j < payload.getOrders().size(); j++) {
                Order order = payload.getOrders().get(j);
                if (order.getOrderType().equals("sell") &&
                        !order.getUser().getStatus().equals("offline") &&
                        order.getPlatform().equals("pc")) {
                    if (order.getPlatinum() < minSellPrice) {
                        minSellPrice = order.getPlatinum();
                        nickInGame = order.getUser().getIngameName();
                    }
                }
            }
            if (minSellPrice == Integer.MAX_VALUE) {
                continue;
            }
            System.out.println(i + ")" + nameOfObjectOnRus + " | " + nickInGame + " | " + minSellPrice);
        }
    }

    private static Payload getPayload(Map<String, String> urlMap, int i) {

        String path = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\";
        List<File> pathsFromDirectory = NameParser.getPathsFromDirectory(path);
        Payload payload = null;
        try {
            String p = pathsFromDirectory.get(i).getPath();
            if (p.equals("C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\Миса Прайм")) {
                i++;
            }
            String json = Files.readString(Paths.get(p));
            JsonParser jsonParser = new JsonParser();
            JsonObject root = jsonParser.parse(json).getAsJsonObject().getAsJsonObject();
            Gson gson = new Gson();
            payload = gson.fromJson(root.getAsJsonObject().get("payload"), Payload.class);
            payload.setNameOfProduct(urlMap.get(pathsFromDirectory.get(i).getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }


    private static Map<String, String> makeMap(List<File> fileList) throws IOException {
        JsonParser jsonParser = new JsonParser();
        String json = "";
        Map<String, String> map = new HashMap<>();
        for (File file : fileList) {
            json = Files.readString(Paths.get(file.getPath()));
            String url = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\";
            String value = "";
            url += jsonParser.parse(json).getAsJsonObject().get("url_name").toString().substring(1, jsonParser.parse(json).getAsJsonObject().get("url_name").toString().length() - 1) + ".json";
            value = String.valueOf(jsonParser.parse(json).getAsJsonObject().get("ru").getAsJsonObject().get("item_name"));
            map.putIfAbsent(url, value);
        }
        return map;
    }

    private static List<File> makeListOfFilesFromItemsDirectory() {
        return NameParser.scanDirectory(NameParser.pathToItems);
    }

}
