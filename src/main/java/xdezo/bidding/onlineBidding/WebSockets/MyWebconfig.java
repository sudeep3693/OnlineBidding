package xdezo.bidding.onlineBidding.WebSockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xdezo.bidding.onlineBidding.Services.JWTService;
import xdezo.bidding.onlineBidding.Utils.UserDetailHolder;

import java.util.concurrent.ConcurrentHashMap;

import static xdezo.bidding.onlineBidding.Utils.UserDetailHolder.getUsername;
import static xdezo.bidding.onlineBidding.Utils.UserDetailHolder.userHolder;

public class MyWebconfig extends TextWebSocketHandler  {


    private final ConcurrentHashMap<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
    @Autowired

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        sessions.put(session.getId(), session);
        System.out.println("New session connected: " + session.getAttributes().get("user"));
        session.sendMessage(new TextMessage("Welcome! Your session Name is "+ session.getAttributes().get("user")));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("new Message received from"+ session.getAttributes().get("user"));
        String payload = (String)message.getPayload();

        for(WebSocketSession s:sessions.values()){
            s.sendMessage(new TextMessage(session.getAttributes().get("user")+": "+payload));
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
