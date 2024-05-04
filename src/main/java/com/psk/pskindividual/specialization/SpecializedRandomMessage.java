package com.psk.pskindividual.specialization;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Specializes;

@RequestScoped
@Specializes
public class SpecializedRandomMessage extends RandomMessage {

}
