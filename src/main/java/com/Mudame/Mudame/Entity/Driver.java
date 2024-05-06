package com.Mudame.Mudame.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;
    @Enumerated (EnumType.STRING)
    private String licenseType; //Enum
    @Enumerated (EnumType.STRING)
    private int license; //Enum
    @Enumerated (EnumType.STRING)
    private  int auxiliar; //Enumasjdajsd
    private int userID;

}
