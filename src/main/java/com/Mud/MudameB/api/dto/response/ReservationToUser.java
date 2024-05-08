package com.Mud.MudameB.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationToUser {
    private Long id;
    private LocalDateTime dateTime;
    private String origin;
    private String destiny;
    private TruckResp truck;
    private DriverResp driver;
}
