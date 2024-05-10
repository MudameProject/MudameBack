package com.Mud.MudameB.api.dto.response;

import com.Mud.MudameB.Utils.enums.Auxiliar;
import com.Mud.MudameB.Utils.enums.LicenseType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverResp {
    private Long id;
    private LicenseType licenseType;
    private String license;
    private Auxiliar auxiliar;
    private UserResp userID;
}
