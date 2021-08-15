package ru.sa.shophelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
+3 за [Эндо]([нидус]/{[некрос]с стяжкой})[некрос]с бустом и с [рипкас]:suda:
+1 за [Эндо][некрос]с бустом и с [рипкас]:suda:
+за [Эндо]([нидус]/{[некрос]с стяжкой}),бустом и[рипкас]:suda:
+за [Эндо][нидус]/[некрос]с стяжкой:suda:
please commend me on market:heart:

Куплю[мистический мститель]:trading::platinum:6:platinum::trading:за 1 сток,(нужно 11)
:platinum::trading:Скупаю прайм моды предложения в лс :trading::platinum:

Куплю прайм хлам:trading:(6*45:ducats:=12:platinum:):trading:

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sellOrBuy;
        while (true) {
            {

                System.out.println("Выберите действие введя цифру");
                System.out.println("1.Покупка");
                System.out.println("2.Продажа");
                sellOrBuy = reader.readLine();

            }
            if (sellOrBuy.equals("1") || sellOrBuy.equals("2")) {
                if (sellOrBuy.equals("1")) {
                    sellOrBuy = "Куплю";
                }

                if (sellOrBuy.equals("2")) {
                    sellOrBuy = "Продам";
                }
                break;
            }
        }
        System.out.println("Введите товар");
        String product = reader.readLine();
        String productReadyToSelling = "[" + product + "]";
        System.out.println("Введите цену");
        String price = reader.readLine();
        System.out.println("Желаете добавить что-нибудь ещё?");
        System.out.println("Если же нет просто нажмите Enter");
        String anythingMore = reader.readLine();

        String fullSellMessage = sellOrBuy + productReadyToSelling + ":trading::platinum:" + price + ":platinum::trading:" + anythingMore;
        if (checkLength(fullSellMessage)) {
            System.out.println(fullSellMessage);
        } else {
            System.out.println("Длинна превышает допустимую " + fullSellMessage.length() + "/180");
        }
    }

    private static boolean checkLength(String parameter) {
        int lengthOfParameter = parameter.length();
        return lengthOfParameter <= 180;
    }
}
