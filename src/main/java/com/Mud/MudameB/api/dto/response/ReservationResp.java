package com.Mud.MudameB.api.dto.response;

import java.time.LocalDateTime;

import com.Mud.MudameB.Utils.enums.Capacity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResp {
  private Long id;
  private LocalDateTime dateTime;
  private String origin;
  private String destiny;
  private BasicClient user;
  private String plate;
  private String model;
  private String brand;
  private String color;
  private Capacity capacity;
  private DriverResp driver;
  private BasicClient client;
}
