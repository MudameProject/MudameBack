package com.Mud.MudameB.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicUser {
    private Long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private Integer phoneNumber;
    private String address;
    private Integer zipCode;
}
