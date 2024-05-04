package com.psk.pskindividual.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentResponse {
    private Integer Id;
    private Long StudentNumber;
    private String Name;
    private String Surname;
    private Integer UniversityId;
}
