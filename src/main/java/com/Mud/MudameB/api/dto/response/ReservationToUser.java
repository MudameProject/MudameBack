package com.Mud.MudameB.api.dto.response;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationToUser {
    private int id;
    private Date date;
    private String origin;
    private String destiny;
    private Time hour;
    private TruckResp truck;
    private DriverResp driver;
}
