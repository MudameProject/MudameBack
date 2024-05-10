package com.Mud.MudameB.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity(name = "truck")
@AllArgsConstructor
@NoArgsConstructor
public class TruckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plate;
    private String model;
    private String brand;
    private String color;
    private String capacity;
    private Long idDriver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverEntity driver;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "truck",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<ReservationEntity> reservation;
}