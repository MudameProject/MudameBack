package com.Mud.MudameB.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.Mud.MudameB.Utils.enums.PayMethod;

import io.micrometer.common.lang.NonNull;

@Entity(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private String origin;
    private String destiny;
    @Column (nullable = false)
    private BigDecimal price;
    @NonNull
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;
    /*Relaciones*/

    // Relacion con truck
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private TruckEntity truck;

    // Relacion con client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    // Relacion con driver
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverEntity driver;
}