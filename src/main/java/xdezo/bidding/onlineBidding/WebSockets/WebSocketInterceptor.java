package xdezo.bidding.onlineBidding.WebSockets;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import xdezo.bidding.onlineBidding.Services.JWTService;
import xdezo.bidding.onlineBidding.Utils.UserDetailHolder;


import java.util.Map;

    public class WebSocketInterceptor implements HandshakeInterceptor {

        @Autowired
        private final JWTService jwtService;

        @Autowired
        private UserDetailHolder userDetailHolder;

        public WebSocketInterceptor(JWTService jwtService) {
            this.jwtService = jwtService;
        }

        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Map<String, Object> attributes) {
            if (request instanceof ServletServerHttpRequest) {
                HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
                String token = servletRequest.getHeader("token");

                System.out.println(token);
                if (token != null) {
                   // Remove "Bearer " prefix
                    if (jwtService.validateToken(token, jwtService.getUserName(token))) {
                        attributes.put("user", jwtService.getUserName(token));
                        System.out.println("auth success");
                        return true; // Allow WebSocket connection
                    }
                    else{
                        System.out.println("auth failed");
                    }
                }
            }
            response.setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            System.out.println("unauthorized");
            return false; // Reject connection
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Exception exception) {
            // No post-handshake logic needed
        }
    }


