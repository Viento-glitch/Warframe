package ru.sa.warframeparcer;

public class Client {
    public static void main(String[] args) {
        //временный main точку входа перенести в отдельный класс отвечающий за запуск приложеня
        run();
    }

    public static void run() {
        AuthorizationManager authorizationManager = new AuthorizationManager();

        UrlClientObjectManager urlClientObjectManager = new UrlClientObjectManager();

    }
}
