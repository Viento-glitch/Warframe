package ru.sa;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersParser {
    public static void main(String[] args) throws IOException {
//        executeParsing();
//        List<File> fileList = makeListOfFilesFromItemsDirectory();
//        Map<String, String> urlMap = makeMap(fileList);
//        for (int i = 0; i < urlMap.size(); i++) {
//            Payload payload = getPayload(urlMap, i);
//            System.out.println(payload.getNameOfProduct());
//        }


        parsePrimeMods();

    }

    private static void parsePrimeMods() throws IOException {
        List<Item> primeMods = PrimeModeManager.getItems();
        String pathToResults = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\resultsOfMods\\";
        List<Payload> payloads = parseUrls(primeMods, pathToResults);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вам будет выдан список лучших ордеров прайм модов");
        String result = "0";
        boolean typeOfOrders = false;
        while (!result.equals("1") && !result.equals("2")) {
            System.out.println("Выберите тип ордеров");
            System.out.println("1.Посмотреть продажи");
            System.out.println("2.Посмотреть покупки");
            result = bufferedReader.readLine();
        }
        if (result.equals("1")) typeOfOrders = true;
        if (result.equals("2")) typeOfOrders = false;
        parsePayloads(payloads, typeOfOrders);
    }

    private static void parsePayloads(List<Payload> payloads, boolean takeSell) {
        String orderType;
        int bestPrice;
        for (int i = 0; i < payloads.size(); i++) {
            if (takeSell) {
                bestPrice = Integer.MAX_VALUE;
                orderType = "sell";
            } else {
                bestPrice = Integer.MIN_VALUE;
                orderType = "buy";
            }
            List<Order> orders = payloads.get(i).getOrders();
            int bestOrderId = 0;
            for (int j = 0, ordersSize = orders.size(); j < ordersSize; j++) {
                Order order = orders.get(j);
                if (order.getOrderType().equals(orderType) &&
                        order.getPlatform().equals("pc") &&
                        !order.getUser().getStatus().equals("offline")) {
                    if (takeSell) {
                        if (bestPrice > order.getPlatinum()) {
                            bestOrderId = j;
                            bestPrice = order.getPlatinum();
                        }
                    } else {
                        if (bestPrice < order.getPlatinum()) {
                            bestOrderId = j;
                            bestPrice = order.getPlatinum();
                        }
                    }
                }
            }
            int modeRank = payloads.get(i).getOrders().get(bestOrderId).getModRank();
            int[] ando = new int[]{40920, 40880, 40800, 40640, 40320, 39680, 38400, 35840, 30720, 20480, 0};
            if (bestPrice != Integer.MIN_VALUE && bestPrice != Integer.MAX_VALUE) {
                System.out.println(i + 1 + ") " + payloads.get(i).getNameOfProduct() + " \n" +
                        "" + payloads.get(i).getOrders().get(bestOrderId).getUser().getIngameName() + " " + bestPrice + "\n" +
                        "Лвл мода: " + modeRank + "/10");
                if (modeRank != 10) {
                    System.out.println("Для заполнения потребуется добавить :" + ando[modeRank] + " Эндо");
                }
                System.out.println("-------------------------------------------------------------------------------");

            }
        }
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
