package org;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

@ClientEndpoint
public class CustomClientSocket {

    @OnOpen
    public void onConnection(Session session) throws Exception {
        System.out.println("connected");
        session.getBasicRemote().sendText("Hello");
    }

    @OnMessage
    public String onMessage(Session session, String msg) {

        System.out.println("message");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @OnClose
    public void close(Session session, CloseReason closeReason) {
        System.out.println("disconnected");
    }


    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ClientManager clientManager = new ClientManager();
        clientManager.connectToServer(CustomClientSocket.class, new URI("ws://localhost:9090/websockets/chat"));
        countDownLatch.await();
    }

}


