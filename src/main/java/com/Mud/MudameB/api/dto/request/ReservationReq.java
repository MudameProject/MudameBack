package com.Mud.MudameB.api.dto.request;

import com.Mud.MudameB.api.dto.response.BasicUser;
import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.api.dto.response.TruckResp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class ReservationReq {
    @NotBlank(message = "la hora y la fecha es requerida")
    private LocalDateTime dateTime;
    @NotBlank(message = "el origen del traslado es obligatorio")
    private String Origin;
    @NotBlank(message = "el destino del traslado es obligatorio")
    private String destiny;
    @NotNull(message = "el id del usuario es obligatorio")
    private int userId;
    @NotNull(message = "el id del camion es obligatorio")
    private int truckId;
    @NotNull(message = "el id del conductor es obligatorio")
    private int driverId;

}
