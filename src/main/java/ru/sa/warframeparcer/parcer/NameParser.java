package ru.sa.warframeparcer.parcer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameParser {
    static String pathToItems = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\tracked\\items\\";

    @NotNull
    static ArrayList<File> getPathsFromDirectory(String path) {
        List<File> files = scanDirectory(path);
        return new ArrayList<>(files);
    }
    static List<File> scanDirectory(String path) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        return Arrays.asList(arrFiles != null ? arrFiles : new File[0]);
    }

    static List<JsonObject> getListJsonObjects(String path, List<File> listOfNames) {
        String json;
        List<JsonObject> list = new ArrayList<>();
        for (File listOfItems : listOfNames) {
            try {
                String name = listOfItems.getName();
                json = Files.readString(Paths.get(path + name));
                list.add(new JsonParser().parse(json).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        return list;
    }
}
