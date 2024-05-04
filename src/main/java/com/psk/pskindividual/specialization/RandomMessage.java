package com.psk.pskindividual.specialization;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RandomMessage {
    public String loadMessage(){
        return "Just a message.";
    }
}
