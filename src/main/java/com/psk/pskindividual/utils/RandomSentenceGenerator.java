package com.psk.pskindividual.utils;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class RandomSentenceGenerator implements Serializable {
    private final String[] words = {
        "banana", "apple", "cat", "sun", "moon",
        "happy", "jump", "run", "sing", "dance"
    };

    public String generateRandomSentence() {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            System.out.println("Exception thrown: " + e);
        }

        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomIndex = new Random().nextInt(words.length);
            sentence.append(words[randomIndex]).append(" ");
        }
        return sentence.toString().trim();
    }
}
