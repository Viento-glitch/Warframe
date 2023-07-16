package ru.sa.warframeparcer.parcer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    static List<Item> getItems() {
        List<JsonObject> list = NameParser.getListJsonObjects(NameParser.pathToItems, NameParser.getPathsFromDirectory(NameParser.pathToItems));
        Gson gson = new Gson();
        List<Item> items = new ArrayList<>();
        for (JsonObject jsonObject : list) {
            Item item = gson.fromJson(jsonObject, Item.class);
            List<String> tags = item.getTags();
            if (!isAMod(tags)) {
                items.add(item);
            }
        }
        return items;
    }

    static List<Item> getMods() {
        List<JsonObject> list = NameParser.getListJsonObjects(NameParser.pathToItems, NameParser.getPathsFromDirectory(NameParser.pathToItems));
        Gson gson = new Gson();
        List<Item> listOfMods = new ArrayList<>();
        for (JsonObject jsonObject : list) {
            Item item = gson.fromJson(jsonObject, Item.class);
            List<String> tags = item.getTags();
            if (isAMod(tags)) {
                listOfMods.add(item);
            }
        }
        return listOfMods;
    }

    private static boolean isAMod(List<String> tags) {
        return (tags.contains("prime") || tags.contains("primary") || tags.contains("sentinel")) && tags.contains("mod") && tags.contains("legendary");
    }
}
