package com.Mud.MudameB.Domain.Entity;

import com.Mud.MudameB.Utils.enums.Auxiliar;
import com.Mud.MudameB.Utils.enums.LicenseType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated (EnumType.STRING)
    private LicenseType licenseType; //Enum
    @Enumerated (EnumType.STRING)
    private LicenseType license; //Enum
    @Enumerated (EnumType.STRING)
    private Auxiliar auxiliar; //Enum
    private int userID;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Truck> trucks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

}