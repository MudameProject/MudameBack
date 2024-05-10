package com.Mud.MudameB.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationReq {
    @NotBlank(message = "la hora y la fecha es requerida")
    private LocalDateTime dateTime;
    @NotBlank(message = "el origen del traslado es obligatorio")
    private String Origin;
    @NotBlank(message = "el destino del traslado es obligatorio")
    private String destiny;
    @NotNull(message = "el id del usuario es obligatorio")
    private Long userId;
    @NotNull(message = "el id del camion es obligatorio")
    private Long truckId;
    @NotNull(message = "el id del conductor es obligatorio")
    private Long driverdI;
}
