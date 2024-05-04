package com.psk.pskindividual.decorators;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class Quote implements IQuote {
    @Override
    public String showQuote(){
        return "Your life matters.";
    }
}
