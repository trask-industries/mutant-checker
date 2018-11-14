package com.traskindustries.messaging;

import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    public void receiveMessage(String message) {
        System.out.println("<<<< Received <" + message + ">");
    }
}
