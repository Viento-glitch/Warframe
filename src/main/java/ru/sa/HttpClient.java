package ru.sa;

import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {
    public static void main(String[] args) {
        int a = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(a);
            a = list.get(i) * 2;
//            System.out.println(a);
        }
        int x = 10;
        int b = 0;
        while (x > 0) {
            b += list.get(x);
//            System.out.print(b);
//            System.out.print(",");
            x--;
        }

        int t = 0;
        for (int i = list.size() - 1; i > 0; i--) {
            System.out.println(t += list.get(i));
        }


    }


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
