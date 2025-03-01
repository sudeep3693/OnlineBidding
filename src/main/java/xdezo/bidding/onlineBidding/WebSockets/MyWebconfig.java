package xdezo.bidding.onlineBidding.WebSockets;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xdezo.bidding.onlineBidding.Services.JWTService;

import java.util.concurrent.ConcurrentHashMap;

public class MyWebconfig extends TextWebSocketHandler  {

    private final ConcurrentHashMap<String,WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        sessions.put(session.getId(), session);
        System.out.println("New session connected: " + session.getId());
        session.sendMessage(new TextMessage("Welcome! Your session Id  is " + session.getId()));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("new Message received from"+ session.getId());
        String payload = (String)message.getPayload();

        for(WebSocketSession s:sessions.values()){
            s.sendMessage(new TextMessage(session.getId()+": "+payload));
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{

        sessions.remove(session.getId());
        for(WebSocketSession s:sessions.values()){
            s.sendMessage(new TextMessage(session.getId()+" Left the chat"));
        }


    }
}
