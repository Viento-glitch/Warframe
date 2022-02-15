package ru.sa;

import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpClient {
    public static void main(String[] args) throws Exception {
//        HttpClient httpClient = new HttpClient();
//        Document document = httpClient.getJsonFrom("https://api.warframe.market/v1/items/zaw_riven_mod_(veiled)/orders");
//        System.out.println(document);
//        JsonParser jsonParser = new JsonParser();
    }


//    Document getJsonFrom(String url) throws Exception {
//        Document document = null;
//        try {
//            document = Jsoup.connect(url).ignoreContentType(true).userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
//            return document;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//            //            throw new Exception("");
//        }
//    }

    public String readJsonFromUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}
