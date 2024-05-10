package com.Mud.MudameB.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TruckResp {
    private Long id;
    private String plate;
    private String model;
    private String brand;
    private String color;
    private String capacity;
    private Long idDriver;
}
