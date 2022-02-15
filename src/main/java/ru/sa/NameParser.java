package ru.sa;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NameParser {
    static String pathToUrl = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\url.txt";
    static String pathToItems = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\tracked\\items\\";
    static String pathToResult = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\";

    public static void main(String[] args) throws Exception {

        ;

//        firstMissionMakingUrlFile();

        List<File> filePaths = getPathsFromDirectory(pathToItems);
        List<String> listOfNames = getNamesToUrl(pathToItems, filePaths);

        makeListUnDownloaded(listOfNames, pathToResult);

//        getListOfUnDownloaded("C:\\Users\\Viento\\Downloads\\wfm-items-master\\un_downloaded.txt");

        List<String> unDownloadedFiles = readTxt("C:\\Users\\Viento\\Downloads\\wfm-items-master\\un_downloaded.txt");


        Date startDate = new Date();
        fixUnDownloaded(listOfNames, pathToUrl, unDownloadedFiles);
//        makeSiteJsonUrls(listOfNames, pathToUrl);
        Date endDate = new Date();
        System.out.println("\nГотово!");
        System.out.println("Времени затрачено:" + timeToString(endDate.getTime() - startDate.getTime()));
    }

    private static List<String> getListOfUnDownloaded(String path) throws ParseException {
        try {
            return readTxt(path);

        } catch (Exception e) {
            throw new ParseException("Can't read file at:" + path, 1);
        }
    }

    private static void makeListUnDownloaded(List<String> listOfNames, String path) {
        List<File> fileList = scanDirectory(path);
        for (int i = 0; i < listOfNames.size(); i++) {
            String name = path + listOfNames.get(i) + ".json";
            if (!fileList.toString().contains(name)) {
                System.out.println(name);
                System.out.println(fileList.get(i));
                makeTxtFile(listOfNames.get(i) + "\n", "C:\\Users\\Viento\\Downloads\\wfm-items-master\\un_downloaded.txt");
//                System.out.println(name);
            }
        }
    }

    private static void firstMissionMakingUrlFile(String path) {
        String pathToUrl = "C:\\Users\\Viento\\Downloads\\wfm-items-master\\url.txt";
        List<File> filePaths = getPathsFromDirectory(pathToUrl);
        List<String> namesToUrl = getNamesToUrl(path, filePaths);
        makeFileWithUrls(namesToUrl, pathToUrl);
    }

    @NotNull
    static List<File> getPathsFromDirectory(String path) {
        List<File> files = scanDirectory(path);
        List<File> filePaths = new ArrayList();
        for (int i = 0; i < files.size(); i++) {
            filePaths.add(files.get(i));
        }
        return filePaths;
    }

    @NotNull
    private static List<String> getNamesToUrl(String path, List<File> listOfNameObject) {
        List<JsonObject> listOfNamesObjects = getListJsonObjects(path, listOfNameObject);
        List<String> namesToUrl = new ArrayList<>();
        for (int i = 0; i < listOfNamesObjects.size(); i++) {
            String name = String.valueOf(listOfNamesObjects.get(i).get("url_name"));
            namesToUrl.add(name.substring(1, name.length() - 1));
        }
        return namesToUrl;
    }

    private static void makeFileWithUrls(List<String> namesToUrl, String pathToUrl) {
        List<String> urls = makeUrls(namesToUrl);
        String url = "";
        for (int i = 0; i < urls.size(); i++) {
            url += urls.get(i) + "\n";
        }
        makeTxtFile(url, pathToUrl);
    }

    static List<File> scanDirectory(String path) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        List<File> listOfNames = Arrays.asList(arrFiles);
        return listOfNames;
    }


    private static void fixUnDownloaded(List<String> listOfNames, String pathToUrl, List<String> unDownloaded) throws Exception {
        List<String> urls = readTxt(pathToUrl);
        HttpClient httpClient = new HttpClient();
        for (int i = 0; i < urls.size(); i++) {
            System.out.println(i + 1 + "/" + urls.size());
            if (unDownloaded.contains(urls.get(i))) {
                continue;
            }
            try {
                System.out.println(urls.get(i));
                String json = httpClient.readJsonFromUrl(urls.get(i));
                makeTxtFile(json, "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\" + listOfNames.get(i) + ".json");
                Thread.sleep(350);
            } catch (Exception e) {
                e.printStackTrace();
                updateTextFile(urls.get(i), "C:\\Users\\Viento\\Downloads\\wfm-items-master\\un_downloaded.txt");
            }
        }
    }

    private static String timeToString(long secs) {
        secs = secs / 1000;
        long hour = secs / 3600;
        long min = secs / 60 % 60;
        long sec = secs % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    private static void makeSiteJsonUrls(List<String> listOfNames, String pathToUrl) throws Exception {
        List<String> urls = readTxt(pathToUrl);
        HttpClient httpClient = new HttpClient();
        for (int i = 0; i < urls.size(); i++) {

            try {
                System.out.println(urls.get(i));
                String json = httpClient.readJsonFromUrl(urls.get(i));
                makeTxtFile(json, "C:\\Users\\Viento\\Downloads\\wfm-items-master\\MYresults\\" + listOfNames.get(i) + ".json");
                Thread.sleep(350);
            } catch (Exception e) {
                e.printStackTrace();
                updateTextFile(urls.get(i), "C:\\Users\\Viento\\Downloads\\wfm-items-master\\un_downloaded.txt");
            }
        }
    }

    private static List<String> readTxt(String pathToFile) throws Exception {
        List<String> result;
        try {
            result = Files.readAllLines((Paths.get(pathToFile)));
            return result;
        } catch (IOException e) {
            throw new Exception("Failed try read urls file on:" + "\t" + pathToFile);
        }
    }

    private static void makeTxtFile(String text, String fileName) {
        if (fileName.equals("failedUrls.txt")) {
        }
        makeTextFile(fileName);
        updateTextFile(text, fileName);
    }

    private static void updateTextFile(String text, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void makeTextFile(String newFilename) {
        File f = new File(newFilename);
        try {
            if (f.createNewFile()) System.out.println("File created.\n File name:" + newFilename);
            else System.err.println("File not Created");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<String> makeUrls(List<String> listOfNames) {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < listOfNames.size(); i++) {
            String url = listOfNames.get(i);
            urls.add("https://api.warframe.market/v1/items/" + url + "/orders");
        }
        return urls;
    }

    private static String getUrlName(List<JsonObject> jsonList, int iterator) {
        return jsonList.get(iterator).getAsJsonObject().get("url_name").toString();
    }

    static List<JsonObject> getListJsonObjects(String path, List<File> listOfNames) {
        String json;
        List<JsonObject> list = new ArrayList();
        for (int i = 0; i < listOfNames.size(); i++) {
            try {
                String name = listOfNames.get(i).getName();
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
