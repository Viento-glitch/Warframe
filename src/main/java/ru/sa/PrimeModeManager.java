package ru.sa;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class PrimeModeManager {
    public static void main(String[] args) {
        List<Item> itemList = getItems();
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(itemList.get(i).getRu().getItemName());
        }
    }

    static List<Item> getItems() {
        List<JsonObject> list = NameParser.getListJsonObjects(NameParser.pathToItems, NameParser.getPathsFromDirectory(NameParser.pathToItems));
        Gson gson = new Gson();
        List<Item> ruListOfItems = new ArrayList<>();
        for (JsonObject jsonObject : list) {
            Item item = gson.fromJson(jsonObject, Item.class);
            List<String> tags = item.getTags();
            if ((tags.contains("prime") || tags.contains("primary")) && tags.contains("mod") && tags.contains("legendary")) {
                ruListOfItems.add(item);
            }
        }
        return ruListOfItems;
    }
}
