package ru.sa;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.google.gson.Gson;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import ru.sa.gen.WarframePacket;

/**
 * This example demonstrates how to create a websocket connection to a server. Only the most
 * important callbacks are overloaded.
 */
public class ExampleClient extends WebSocketClient {

    private static final Gson gson = new Gson();
    private boolean receivedFirstMessage;

    public ExampleClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ExampleClient(URI serverURI) {
        super(serverURI);
    }

    public ExampleClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        if (!receivedFirstMessage) {
            send("{\"type\":\"@WS/USER/SET_STATUS\",\"payload\":\"ingame\"}");
//            send("{\"type\":\"@WS/SUBSCRIBE/MOST_RECENT\"}");

            receivedFirstMessage = true;
        } else {
            WarframePacket warframePacket = gson.fromJson(message, WarframePacket.class);
            System.out.println(warframePacket.getPayload().getOrder().getOrderType());
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        ExampleClient client = new ExampleClient(new URI("wss://warframe.market/socket?platform=pc"));
        client.connect();
        Thread.sleep(200);
    }
}