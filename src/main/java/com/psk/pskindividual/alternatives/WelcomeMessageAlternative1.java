package com.psk.pskindividual.alternatives;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;

@RequestScoped
@Alternative
public class WelcomeMessageAlternative1 implements IWelcomeMessage {
    @Override
    public String showWelcomeMessage(){
        return "Welcome to this awesome university management page.";
    }
}
