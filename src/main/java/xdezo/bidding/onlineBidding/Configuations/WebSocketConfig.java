package xdezo.bidding.onlineBidding.Configuations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import xdezo.bidding.onlineBidding.Services.JWTService;
import xdezo.bidding.onlineBidding.WebSockets.MyWebconfig;
import xdezo.bidding.onlineBidding.WebSockets.WebSocketInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private JWTService jwtService;

    public WebSocketConfig(JWTService jwtService){
        this.jwtService = jwtService;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebconfig(), "/ws")
                .setAllowedOrigins("*")
                .addInterceptors(new WebSocketInterceptor(jwtService));
    }
}
