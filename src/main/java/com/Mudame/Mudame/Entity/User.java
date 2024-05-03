package com.Mudame.Mudame.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Driver;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private int phoneNumber;
    private String address;
    private int zipCode;
    private int idDocument;

    //Tenemos que tener en cuenta que tipo de Tabla o entidad es para poner la conexion con la otra posible tabla
    //En este caso tenemos la tabla User y Driver en este caso en OneToOne
    @OneToOne(
       mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Driver userDriver;

}


