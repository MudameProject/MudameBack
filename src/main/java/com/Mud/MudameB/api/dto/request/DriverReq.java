package com.Mud.MudameB.api.dto.request;

import com.Mud.MudameB.Utils.enums.Auxiliar;
import com.Mud.MudameB.Utils.enums.LicenseType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverReq {
    @NotBlank(message = "el tipo de licencia es requerido")
    private LicenseType licenseType;
    @NotBlank(message = "el numero de licencia es requerido")
    private LicenseType license;
    private Auxiliar auxiliar;
    @NotBlank(message = "el numero de identidad del empleado es requerido")
    private int userID;
}
