package com.Mud.MudameB.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResp {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String address;
    private Integer zipCode;
    private List<ReservationToClient> reservations;
}
