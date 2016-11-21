package org;


import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")

public class CuctomerServerSocket {

    @OnOpen
    public void onConnect(Session session) {

        System.out.println("Connected ... ");
    }

    @OnMessage
    public String onMessage(Session session, String msg) throws Exception {
        System.out.println("Server :: message " + msg);
        if ("exit".equals(msg))
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "exit"));
        return msg;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {

        System.out.println("Server disconnected " + closeReason.getReasonPhrase());

    }

}
