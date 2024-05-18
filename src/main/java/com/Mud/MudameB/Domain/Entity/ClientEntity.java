package com.Mud.MudameB.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.Mud.MudameB.Utils.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private Integer phoneNumber;
    private String address;
    @Column(length = 10)
    private Integer zipCode;
    @Column(length = 100)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ReservationEntity> reservation;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DriverEntity driver;

}
