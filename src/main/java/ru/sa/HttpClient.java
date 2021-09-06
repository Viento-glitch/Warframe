package ru.sa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HttpClient {

    public static void main(String[] args) throws IOException {

        final String url = "https://warframe.market/ru/items/primed_fever_strike";

        try {
            final Document document =
                    Jsoup.connect(url).userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
            System.out.println(document);
//           Elements table = document.select("div.infinite-scroll");
//            System.out.println(table);
            System.out.println(document);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
