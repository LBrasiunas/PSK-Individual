package com.psk.pskindividual.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

@Decorator
public class QuoteDecorator implements IQuote {
    @Inject @Delegate @Any IQuote quote;

    @Override
    public String showQuote(){
        return quote.showQuote() + " -university association.";
    }
}
