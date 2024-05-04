package com.psk.pskindividual.alternatives;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class WelcomeMessage implements IWelcomeMessage {
    @Override
    public String showWelcomeMessage(){
        return "Welcome to the management page.";
    }
}
