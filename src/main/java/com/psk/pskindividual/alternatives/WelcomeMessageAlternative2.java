package com.psk.pskindividual.alternatives;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;

@RequestScoped
@Alternative
public class WelcomeMessageAlternative2 implements IWelcomeMessage {
    @Override
    public String showWelcomeMessage(){
        return "Welcome to this even more awesome university management page.";
    }
}
