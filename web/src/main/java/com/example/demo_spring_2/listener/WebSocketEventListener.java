package com.example.demo_spring_2.listener;

import com.example.demo_spring_2.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.security.Principal;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
        Principal user = event.getUser();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        logger.info("User Disconnected : ");

//            messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }


    @EventListener
    public void handleWebSocketSubscriptionListener(SessionSubscribeEvent event) {
        logger.info("Received a new web socket subscription");
        Principal user = event.getUser();
        if(user!=null){
            CurrentUser currentUser = (CurrentUser) ((UsernamePasswordAuthenticationToken) user).getPrincipal();
            SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
            String destination = accessor.getDestination();
            String userId = destination.substring(destination.lastIndexOf("/")+1);
            if(currentUser.getUser().getId()!=Integer.parseInt(userId)){ //todo close session


            }
        }else {//todo close session

        }
    }
}
