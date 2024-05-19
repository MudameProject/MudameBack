package com.Mud.MudameB.Domain.Entity;

import com.Mud.MudameB.Utils.enums.Auxiliar;
import com.Mud.MudameB.Utils.enums.LicenseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity(name = "driver")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private Integer phoneNumber;
    @Enumerated(EnumType.STRING)
    private LicenseType licenseType; // Enum
    @NonNull
    @Size(min = 2, max = 10)
    private String license;
    @Enumerated(EnumType.STRING)
    private Auxiliar auxiliar; // Enum

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TruckEntity> trucks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}