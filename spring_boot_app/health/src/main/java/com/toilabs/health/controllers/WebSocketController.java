package com.toilabs.health.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Payload;


@Controller
public class WebSocketController {

    @MessageMapping("/hello") // Client sends to /app/hello
    @SendTo("/topic/greetings") // Broadcasted to subscribers of /topic/greetings
    public String greet(@Payload String message) {
        return "Hello: " + message;
    }
}
