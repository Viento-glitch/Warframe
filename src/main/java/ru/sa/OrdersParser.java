package ru.sa;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OrdersParser {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Payload> payloads = getPayloadList();
        for (int i = 0; i < payloads.size(); i++) {
            for (int j = 0; j < payloads.get(i).getOrders().size(); j++) {
                System.out.println(payloads.get(i).getOrders().get(i));
//                System.out.println(payloads.get(i).orders.get(i).platform);
//                System.out.println(payloads.get(i).orders.get(i).platinum);
//                break;
            }
//            break;
        }
    }

    private static ArrayList<Payload> getPayloadList() {
//        Gson gson = new Gson();

        String path = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\";
        List<File> pathsFromDirectory = NameParser.getPathsFromDirectory(path);
//        List<JsonObject> jsonObjectList = NameParser.getListJsonObjects(path, pathsFromDirectory);
        ArrayList<Payload> payloads = new ArrayList<>();
        for (int i = 0; i < pathsFromDirectory.size(); i++) {
            try {
                String json = Files.readString(Paths.get(pathsFromDirectory.get(i).getPath()));
                JsonObject root = new JsonParser().parse(json).getAsJsonObject();
                if (!root.get("payload").getAsJsonObject().equals(null)) {
                    for (int j = 0; j <root.get("payload")
                            .getAsJsonObject()
                            .getAsJsonArray("orders").size(); j++) {
                        System.out.println(root.get("payload")
                                .getAsJsonObject()
                                .getAsJsonArray("orders").get(j)
                                .getAsJsonObject().get("user")
                                .getAsJsonObject().get("ingame_name"));
                    }

                }

            } catch (Exception e) {
                e.getMessage();
            }


        }


//        for (int i = 0; i < jsonObjectList.size(); i++) {
//        }
        return payloads;
    }
}
