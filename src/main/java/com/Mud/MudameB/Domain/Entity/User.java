package com.Mud.MudameB.Domain.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private Integer phoneNumber;
    private String address;
    @Column(length = 10)
    private Integer zipCode;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Reservation> reservation;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Driver driver;

}
