package com.Mud.MudameB.api.dto.request;

import com.Mud.MudameB.Utils.enums.Capacity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TruckReq {
    @NotNull(message = "el nombre de la placa del camion es requerido")
    private String plate;
    @NotNull(message = "el nombre del modelo del camion es requerido")
    private String model;
    @NotNull(message = "el nombre de la marca del camion es requerido")
    private String brand;
    @NotNull(message = "el color del camion es requerido")    
    private String color;
    @NotNull(message = "la capacidad del camion es requerida")
    private Capacity capacity;
}
