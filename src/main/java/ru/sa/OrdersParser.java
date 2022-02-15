package ru.sa;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersParser {
    public static void main(String[] args) throws IOException {
        executeParsing();
//        List<File> fileList = makeListOfFilesFromItemsDirectory();
//        Map<String, String> urlMap = makeMap(fileList);
//        Payload payload = getPayload(urlMap, 49);
//        System.out.println(        payload.getNameOfProduct());
    }

    private static void executeParsing() throws IOException {
        List<File> fileList = makeListOfFilesFromItemsDirectory();
        Map<String, String> urlMap = makeMap(fileList);

        for (int i = 215; i < urlMap.size(); i++) {
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
            if (minSellPrice == Integer.MAX_VALUE){continue;}
            System.out.println(i + ")" + nameOfObjectOnRus + " | " + nickInGame + " | " + minSellPrice);
        }
    }

    private static Payload getPayload(Map<String, String> urlMap, int i) {

        String path = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\";
        List<File> pathsFromDirectory = NameParser.getPathsFromDirectory(path);
        ArrayList<Payload> payloads = new ArrayList<>();
        Payload payload = null;
        try {
            String p = pathsFromDirectory.get(i).getPath();
            if (p.equals("C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\Миса Прайм")){i++;}
            String json = Files.readString(Paths.get(p));
//            System.out.println(json);
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
        for (int i = 0; i < fileList.size(); i++) {
            json = Files.readString(Paths.get(fileList.get(i).getPath()));
            String url = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\";
            String value = "";
            url += String.valueOf(jsonParser.parse(json).getAsJsonObject().get("url_name").toString().substring(1, jsonParser.parse(json).getAsJsonObject().get("url_name").toString().length() - 1) + ".json");
//            System.out.println(url);
            value = String.valueOf(jsonParser.parse(json).getAsJsonObject().get("ru").getAsJsonObject().get("item_name"));
            map.putIfAbsent(url, value);
        }
        return map;
    }

    private static List<File> makeListOfFilesFromItemsDirectory() {
        return NameParser.scanDirectory(NameParser.pathToItems);
    }

}
